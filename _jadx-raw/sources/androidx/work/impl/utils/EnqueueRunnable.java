package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes14.dex */
public class EnqueueRunnable {
    private static final String TAG = Logger.tagWithPrefix("EnqueueRunnable");

    private EnqueueRunnable() {
    }

    public static void enqueue(WorkContinuationImpl workContinuation) {
        if (workContinuation.hasCycles()) {
            throw new IllegalStateException("WorkContinuation has cycles (" + workContinuation + ")");
        }
        boolean needsScheduling = addToDatabase(workContinuation);
        if (needsScheduling) {
            scheduleWorkInBackground(workContinuation);
        }
    }

    public static boolean addToDatabase(WorkContinuationImpl workContinuation) {
        WorkManagerImpl workManagerImpl = workContinuation.getWorkManagerImpl();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        workDatabase.beginTransaction();
        try {
            EnqueueUtilsKt.checkContentUriTriggerWorkerLimits(workDatabase, workManagerImpl.getConfiguration(), workContinuation);
            boolean needsScheduling = processContinuation(workContinuation);
            workDatabase.setTransactionSuccessful();
            return needsScheduling;
        } finally {
            workDatabase.endTransaction();
        }
    }

    public static void scheduleWorkInBackground(WorkContinuationImpl workContinuation) {
        WorkManagerImpl workManager = workContinuation.getWorkManagerImpl();
        Schedulers.schedule(workManager.getConfiguration(), workManager.getWorkDatabase(), workManager.getSchedulers());
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuation) {
        boolean needsScheduling = false;
        List<WorkContinuationImpl> parents = workContinuation.getParents();
        if (parents != null) {
            for (WorkContinuationImpl parent : parents) {
                if (!parent.isEnqueued()) {
                    needsScheduling |= processContinuation(parent);
                } else {
                    Logger.get().warning(TAG, "Already enqueued work ids (" + TextUtils.join(", ", parent.getIds()) + ")");
                }
            }
        }
        return needsScheduling | enqueueContinuation(workContinuation);
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuation) {
        Set<String> prerequisiteIds = WorkContinuationImpl.prerequisitesFor(workContinuation);
        boolean needsScheduling = enqueueWorkWithPrerequisites(workContinuation.getWorkManagerImpl(), workContinuation.getWork(), (String[]) prerequisiteIds.toArray(new String[0]), workContinuation.getName(), workContinuation.getExistingWorkPolicy());
        workContinuation.markEnqueued();
        return needsScheduling;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:109:0x01de  */
    /* JADX WARN: Code duplicated, block: B:112:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:115:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:117:0x01fe A[LOOP:4: B:116:0x01fc->B:117:0x01fe, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:119:0x0226  */
    /* JADX WARN: Code duplicated, block: B:122:0x023d  */
    /* JADX WARN: Code duplicated, block: B:136:0x024d A[SYNTHETIC] */
    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl, List<? extends WorkRequest> workList, String[] prerequisiteIds, String name, ExistingWorkPolicy existingWorkPolicy) {
        boolean needsScheduling;
        Iterator<? extends WorkRequest> it;
        WorkRequest work;
        WorkSpec workSpec;
        String[] prerequisiteIds2;
        Iterator<? extends WorkRequest> it2;
        long currentTimeMillis;
        int length;
        int i;
        String[] prerequisiteIds3 = prerequisiteIds;
        boolean needsScheduling2 = false;
        long currentTimeMillis2 = workManagerImpl.getConfiguration().getClock().currentTimeMillis();
        WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
        boolean hasPrerequisite = prerequisiteIds3 != null && prerequisiteIds3.length > 0;
        boolean hasCompletedAllPrerequisites = true;
        boolean hasFailedPrerequisites = false;
        boolean hasCancelledPrerequisites = false;
        if (!hasPrerequisite) {
            needsScheduling = false;
        } else {
            int length2 = prerequisiteIds3.length;
            int i2 = 0;
            while (i2 < length2) {
                String id = prerequisiteIds3[i2];
                WorkSpec prerequisiteWorkSpec = workDatabase.workSpecDao().getWorkSpec(id);
                if (prerequisiteWorkSpec == null) {
                    Logger.get().error(TAG, "Prerequisite " + id + " doesn't exist; not enqueuing");
                    return false;
                }
                boolean needsScheduling3 = needsScheduling2;
                WorkInfo.State prerequisiteState = prerequisiteWorkSpec.state;
                hasCompletedAllPrerequisites &= prerequisiteState == WorkInfo.State.SUCCEEDED;
                if (prerequisiteState == WorkInfo.State.FAILED) {
                    hasFailedPrerequisites = true;
                } else if (prerequisiteState == WorkInfo.State.CANCELLED) {
                    hasCancelledPrerequisites = true;
                }
                i2++;
                needsScheduling2 = needsScheduling3;
            }
            needsScheduling = needsScheduling2;
        }
        boolean needsScheduling4 = TextUtils.isEmpty(name);
        boolean isNamed = !needsScheduling4;
        boolean shouldApplyExistingWorkPolicy = isNamed && !hasPrerequisite;
        if (shouldApplyExistingWorkPolicy) {
            List<WorkSpec.IdAndState> existingWorkSpecIdAndStates = workDatabase.workSpecDao().getWorkSpecIdAndStatesForName(name);
            if (!existingWorkSpecIdAndStates.isEmpty()) {
                if (existingWorkPolicy == ExistingWorkPolicy.APPEND || existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE) {
                    DependencyDao dependencyDao = workDatabase.dependencyDao();
                    List<String> newPrerequisiteIds = new ArrayList<>();
                    for (WorkSpec.IdAndState idAndState : existingWorkSpecIdAndStates) {
                        boolean hasPrerequisite2 = hasPrerequisite;
                        if (!dependencyDao.hasDependents(idAndState.id)) {
                            boolean hasCompletedAllPrerequisites2 = (idAndState.state == WorkInfo.State.SUCCEEDED) & hasCompletedAllPrerequisites;
                            if (idAndState.state == WorkInfo.State.FAILED) {
                                hasFailedPrerequisites = true;
                            } else if (idAndState.state == WorkInfo.State.CANCELLED) {
                                hasCancelledPrerequisites = true;
                            }
                            newPrerequisiteIds.add(idAndState.id);
                            hasCompletedAllPrerequisites = hasCompletedAllPrerequisites2;
                        }
                        hasPrerequisite = hasPrerequisite2;
                        dependencyDao = dependencyDao;
                    }
                    if (existingWorkPolicy == ExistingWorkPolicy.APPEND_OR_REPLACE && (hasCancelledPrerequisites || hasFailedPrerequisites)) {
                        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                        List<WorkSpec.IdAndState> idAndStates = workSpecDao.getWorkSpecIdAndStatesForName(name);
                        Iterator<WorkSpec.IdAndState> it3 = idAndStates.iterator();
                        while (it3.hasNext()) {
                            workSpecDao.delete(it3.next().id);
                            idAndStates = idAndStates;
                        }
                        newPrerequisiteIds = Collections.emptyList();
                        hasCancelledPrerequisites = false;
                        hasFailedPrerequisites = false;
                    }
                    prerequisiteIds3 = (String[]) newPrerequisiteIds.toArray(prerequisiteIds3);
                    hasPrerequisite = prerequisiteIds3.length > 0;
                } else {
                    if (existingWorkPolicy == ExistingWorkPolicy.KEEP) {
                        Iterator<WorkSpec.IdAndState> it4 = existingWorkSpecIdAndStates.iterator();
                        while (it4.hasNext()) {
                            WorkSpec.IdAndState idAndState2 = it4.next();
                            Iterator<WorkSpec.IdAndState> it5 = it4;
                            if (idAndState2.state == WorkInfo.State.ENQUEUED || idAndState2.state == WorkInfo.State.RUNNING) {
                                return false;
                            }
                            it4 = it5;
                        }
                    }
                    CancelWorkRunnable.forNameInline(name, workManagerImpl);
                    boolean needsScheduling5 = true;
                    WorkSpecDao workSpecDao2 = workDatabase.workSpecDao();
                    Iterator<WorkSpec.IdAndState> it6 = existingWorkSpecIdAndStates.iterator();
                    while (it6.hasNext()) {
                        workSpecDao2.delete(it6.next().id);
                        needsScheduling5 = needsScheduling5;
                        shouldApplyExistingWorkPolicy = shouldApplyExistingWorkPolicy;
                    }
                    needsScheduling = needsScheduling5;
                }
            }
            it = workList.iterator();
            while (it.hasNext()) {
                work = it.next();
                workSpec = work.getWorkSpec();
                if (!hasPrerequisite && !hasCompletedAllPrerequisites) {
                    if (hasFailedPrerequisites) {
                        workSpec.state = WorkInfo.State.FAILED;
                    } else if (hasCancelledPrerequisites) {
                        workSpec.state = WorkInfo.State.CANCELLED;
                    } else {
                        workSpec.state = WorkInfo.State.BLOCKED;
                    }
                } else {
                    workSpec.lastEnqueueTime = currentTimeMillis2;
                }
                if (workSpec.state == WorkInfo.State.ENQUEUED) {
                    needsScheduling = true;
                }
                workDatabase.workSpecDao().insertWorkSpec(EnqueueUtilsKt.wrapWorkSpecIfNeeded(workManagerImpl.getSchedulers(), workSpec));
                if (hasPrerequisite) {
                    prerequisiteIds2 = prerequisiteIds3;
                    it2 = it;
                    currentTimeMillis = currentTimeMillis2;
                } else {
                    length = prerequisiteIds3.length;
                    i = 0;
                    while (i < length) {
                        Iterator<? extends WorkRequest> it7 = it;
                        String prerequisiteId = prerequisiteIds3[i];
                        String[] prerequisiteIds4 = prerequisiteIds3;
                        Dependency dep = new Dependency(work.getStringId(), prerequisiteId);
                        workDatabase.dependencyDao().insertDependency(dep);
                        i++;
                        it = it7;
                        prerequisiteIds3 = prerequisiteIds4;
                        currentTimeMillis2 = currentTimeMillis2;
                    }
                    prerequisiteIds2 = prerequisiteIds3;
                    it2 = it;
                    currentTimeMillis = currentTimeMillis2;
                }
                workDatabase.workTagDao().insertTags(work.getStringId(), work.getTags());
                if (isNamed) {
                    workDatabase.workNameDao().insert(new WorkName(name, work.getStringId()));
                }
                it = it2;
                prerequisiteIds3 = prerequisiteIds2;
                currentTimeMillis2 = currentTimeMillis;
            }
            return needsScheduling;
        }
        hasPrerequisite = hasPrerequisite;
        it = workList.iterator();
        while (it.hasNext()) {
            work = it.next();
            workSpec = work.getWorkSpec();
            if (!hasPrerequisite) {
                workSpec.lastEnqueueTime = currentTimeMillis2;
            } else {
                workSpec.lastEnqueueTime = currentTimeMillis2;
            }
            if (workSpec.state == WorkInfo.State.ENQUEUED) {
                needsScheduling = true;
            }
            workDatabase.workSpecDao().insertWorkSpec(EnqueueUtilsKt.wrapWorkSpecIfNeeded(workManagerImpl.getSchedulers(), workSpec));
            if (hasPrerequisite) {
                prerequisiteIds2 = prerequisiteIds3;
                it2 = it;
                currentTimeMillis = currentTimeMillis2;
            } else {
                length = prerequisiteIds3.length;
                i = 0;
                while (i < length) {
                    Iterator<? extends WorkRequest> it8 = it;
                    String prerequisiteId2 = prerequisiteIds3[i];
                    String[] prerequisiteIds5 = prerequisiteIds3;
                    Dependency dep2 = new Dependency(work.getStringId(), prerequisiteId2);
                    workDatabase.dependencyDao().insertDependency(dep2);
                    i++;
                    it = it8;
                    prerequisiteIds3 = prerequisiteIds5;
                    currentTimeMillis2 = currentTimeMillis2;
                }
                prerequisiteIds2 = prerequisiteIds3;
                it2 = it;
                currentTimeMillis = currentTimeMillis2;
            }
            workDatabase.workTagDao().insertTags(work.getStringId(), work.getTags());
            if (isNamed) {
                workDatabase.workNameDao().insert(new WorkName(name, work.getStringId()));
            }
            it = it2;
            prerequisiteIds3 = prerequisiteIds2;
            currentTimeMillis2 = currentTimeMillis;
        }
        return needsScheduling;
    }
}
