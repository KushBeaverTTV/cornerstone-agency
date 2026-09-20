"""Laya — lazy-loaded RLAgent wrapper (typed-decision routing/triage).

The model dir is added to sys.path so `rl_agent_api` / `rl_common` import.
Torch is only imported on first use — the bridge starts fast and /laya/*
endpoints trigger the ~2-5 s load in a threadpool.
"""
from __future__ import annotations

import asyncio
import logging
import os
import sys
import time
from typing import Any, Dict, List, Optional

log = logging.getLogger("agency_bridge.laya")

URGENCY_LABELS = ["later", "soon", "now"]


class LayaEngine:
    def __init__(self, model_dir: str):
        self.model_dir = model_dir
        self._agent = None
        self._lock = asyncio.Lock()
        self._load_ms: Optional[float] = None
        self._load_error: Optional[str] = None

    @property
    def loaded(self) -> bool:
        return self._agent is not None

    @property
    def load_error(self) -> Optional[str]:
        return self._load_error

    async def _ensure(self):
        if self._agent is not None:
            return self._agent
        async with self._lock:
            if self._agent is not None:
                return self._agent
            t0 = time.time()
            try:
                agent = await asyncio.get_running_loop().run_in_executor(
                    None, self._load_sync
                )
            except Exception as e:  # noqa: BLE001
                self._load_error = f"{type(e).__name__}: {e}"
                log.exception("Laya load failed")
                raise
            self._agent = agent
            self._load_ms = (time.time() - t0) * 1000
            log.info("Laya loaded in %.0f ms", self._load_ms)
            return self._agent

    def _load_sync(self):
        if self.model_dir not in sys.path:
            sys.path.insert(0, self.model_dir)
        from rl_agent_api import RLAgent  # noqa: PLC0415 — lazy by design

        return RLAgent(self.model_dir)

    async def predict(self, state: str, questions: Dict[str, Any]) -> Dict[str, Any]:
        agent = await self._ensure()
        return await asyncio.get_running_loop().run_in_executor(
            None, agent.system_one, state, questions
        )

    def status(self) -> Dict[str, Any]:
        return {
            "loaded": self.loaded,
            "checkpoint": self.model_dir,
            "device": getattr(getattr(self._agent, "device", None), "type", None),
            "warm_ms": self._load_ms,
            "error": self._load_error,
            "present": os.path.isdir(self.model_dir),
        }

    # -- typed helpers -----------------------------------------------------

    async def route(self, text: str, bots: List[Dict[str, str]]) -> Dict[str, Any]:
        """One `choice` question over the roster."""
        criteria: Dict[str, Any] = {
            b["name"]: b.get("description") or b["name"] for b in bots
        }
        if len(criteria) < 2:
            criteria["none"] = "No agent fits"
        questions = {
            "route": {
                "type": "choice",
                "instructions": "Which agent should handle this message?",
                "criteria": criteria,
            }
        }
        out = await self.predict(text, questions)
        ans = out["answers"]["route"]
        return {
            "bot": ans["choice"],
            "probs": ans["probabilities"],
            "confidence": ans["confidence"],
        }

    async def triage(self, title: str, body: str) -> Dict[str, Any]:
        """`score` over later/soon/now + a `noul` needs-human check."""
        state = f"{title}\n\n{body}".strip()
        questions = {
            "urgency": {
                "type": "score",
                "instructions": "How urgently does this need attention?",
                "criteria": URGENCY_LABELS,
            },
            "needs_human": {
                "type": "noul",
                "instructions": "Does this require a human to act right now?",
            },
        }
        out = await self.predict(state, questions)
        urg = out["answers"]["urgency"]
        level = max(0, min(2, int(round(urg["score"]))))
        return {
            "urgency": {
                "level": level,
                "label": URGENCY_LABELS[level],
                "score": urg["score"],
                "probs": urg["probabilities"],
            },
            "needs_human": out["answers"]["needs_human"]["noul"],
        }
