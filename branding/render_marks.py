"""Cornerstone marks. Palette: ink #0A0A0A / signal #FCEE0A / white.

No inner stroke, no micro-labels on icon canvases, no CAD beams.
C is optically shifted left. Adaptive-icon safe zone = 66% center.
"""
from __future__ import annotations

from pathlib import Path

from PIL import Image, ImageDraw, ImageFont

INK = (10, 10, 10, 255)
SIGNAL = (252, 238, 10, 255)
WHITE = (255, 255, 255, 255)
FONT_DIR = Path(r"C:/Users/antho/AppData/Local/hermes/skills/canvas-design/canvas-fonts")
OUT = Path(r"C:/Cornerstone/projects/aurora/branding/logo")
SIZE = 1024


def font(name: str, size: int) -> ImageFont.FreeTypeFont:
    return ImageFont.truetype(str(FONT_DIR / name), size)


def canvas(bg=INK) -> tuple[Image.Image, ImageDraw.ImageDraw]:
    im = Image.new("RGBA", (SIZE, SIZE), bg)
    return im, ImageDraw.Draw(im)


def save(im: Image.Image, stem: str) -> Path:
    p = OUT / f"{stem}.png"
    im.save(p, "PNG")
    return p


def draw_c(d: ImageDraw.ImageDraw, fill, size=560, dx=-18, dy=16):
    f = font("BigShoulders-Bold.ttf", size)
    c = "C"
    bbox = d.textbbox((0, 0), c, font=f)
    tw, th = bbox[2] - bbox[0], bbox[3] - bbox[1]
    tx = SIZE // 2 - tw // 2 - bbox[0] + dx
    ty = SIZE // 2 - th // 2 - bbox[1] + dy
    d.text((tx, ty), c, font=f, fill=fill)
    return tx, ty, tw, th


def mark_keystone() -> Path:
    """Solid yellow keystone. C punched in ink. Nothing else."""
    im, d = canvas()
    cx, cy = SIZE // 2, SIZE // 2
    top_w, bot_w, h = 640, 468, 620
    y0 = cy - h // 2
    y1 = cy + h // 2
    d.polygon(
        [
            (cx - top_w // 2, y0),
            (cx + top_w // 2, y0),
            (cx + bot_w // 2, y1),
            (cx - bot_w // 2, y1),
        ],
        fill=SIGNAL,
    )
    draw_c(d, INK, size=540, dx=-22, dy=8)
    return save(im, "01-keystone")


def mark_corner_cut() -> Path:
    """Square plate, equal 45° shears on opposite corners. C in the plate."""
    im, d = canvas()
    m = 168
    cut = 168  # equal
    pts = [
        (m + cut, m),
        (SIZE - m, m),
        (SIZE - m, SIZE - m - cut),
        (SIZE - m - cut, SIZE - m),
        (m, SIZE - m),
        (m, m + cut),
    ]
    d.polygon(pts, fill=SIGNAL)
    draw_c(d, INK, size=520, dx=-20, dy=10)
    return save(im, "02-corner-cut")


def mark_c_only() -> Path:
    """Just the C. Survives every Android mask. Yellow on ink."""
    im, d = canvas()
    draw_c(d, SIGNAL, size=640, dx=-24, dy=12)
    return save(im, "03-c-only")


def mark_wordmark() -> Path:
    """CORNERSTONE as a word. Yellow square is a corner, not a fake O."""
    im, d = canvas()
    f = font("Outfit-Bold.ttf", 78)
    word = "CORNERSTONE"
    bbox = d.textbbox((0, 0), word, font=f)
    tw = bbox[2] - bbox[0]
    x = (SIZE - tw) // 2 - bbox[0]
    y = SIZE // 2 - 56
    d.text((x, y), word, font=f, fill=WHITE)

    # yellow corner sitting on the first C — identity, not a letter swap
    o = 22
    d.polygon([(x - 8, y - 10), (x - 8 + o, y - 10), (x - 8, y - 10 + o)], fill=SIGNAL)

    mf = font("IBMPlexMono-Bold.ttf", 28)
    sub = "AGENCY"
    sb = d.textbbox((0, 0), sub, font=mf)
    sw = sb[2] - sb[0]
    d.text(((SIZE - sw) // 2 - sb[0], y + 108), sub, font=mf, fill=SIGNAL)
    return save(im, "04-wordmark")


def board(paths: list[Path]) -> Path:
    pad = 40
    label_h = 52
    cell = 480
    w = pad * 3 + cell * 2
    h = 72 + pad * 3 + (cell + label_h) * 2
    im = Image.new("RGBA", (w, h), INK)
    d = ImageDraw.Draw(im)
    title_f = font("IBMPlexMono-Bold.ttf", 20)
    d.text((pad, 24), "CORNERSTONE  ·  FOUR MARKS", font=title_f, fill=SIGNAL)
    names = ["01 KEYSTONE  (icon pick)", "02 CORNER CUT", "03 C ONLY  (mask-safe)", "04 WORDMARK"]
    lf = font("IBMPlexMono-Regular.ttf", 18)
    for i, p in enumerate(paths):
        col, row = i % 2, i // 2
        x = pad + col * (cell + pad)
        y = 72 + pad + row * (cell + label_h + pad)
        mark = Image.open(p).convert("RGBA").resize((cell, cell), Image.Resampling.LANCZOS)
        im.paste(mark, (x, y))
        d.rectangle((x, y, x + cell - 1, y + cell - 1), outline=(40, 40, 40, 255), width=1)
        d.text((x, y + cell + 14), names[i], font=lf, fill=WHITE)
    out = OUT / "board.png"
    im.save(out, "PNG")
    return out


def main() -> None:
    OUT.mkdir(parents=True, exist_ok=True)
    paths = [mark_keystone(), mark_corner_cut(), mark_c_only(), mark_wordmark()]
    board(paths)
    for p in paths:
        print(p.name, p.stat().st_size)


if __name__ == "__main__":
    main()
