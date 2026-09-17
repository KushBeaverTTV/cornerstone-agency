package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.MoaConfigDto;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lai/nous/aurora/data/repository/MoaWriteContract;", "", "<init>", "()V", "matches", "", "requested", "Lai/nous/aurora/data/remote/MoaConfigDto;", "verified", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MoaWriteContract {
    public static final int $stable = 0;
    public static final MoaWriteContract INSTANCE = new MoaWriteContract();

    private MoaWriteContract() {
    }

    public final boolean matches(MoaConfigDto requested, MoaConfigDto verified) {
        Intrinsics.checkNotNullParameter(requested, "requested");
        Intrinsics.checkNotNullParameter(verified, "verified");
        if (!Intrinsics.areEqual(requested.getDefaultPreset(), verified.getDefaultPreset()) || !Intrinsics.areEqual(requested.getActivePreset(), verified.getActivePreset()) || requested.getSaveTraces() != verified.getSaveTraces() || !Intrinsics.areEqual(requested.getTraceDir(), verified.getTraceDir()) || !Intrinsics.areEqual(requested.getPresets(), verified.getPresets())) {
            return false;
        }
        if (!requested.getReferenceModels().isEmpty() && !Intrinsics.areEqual(requested.getReferenceModels(), verified.getReferenceModels())) {
            return false;
        }
        if (requested.getAggregator() != null && !Intrinsics.areEqual(requested.getAggregator(), verified.getAggregator())) {
            return false;
        }
        if (requested.getMaxTokens() != null && !Intrinsics.areEqual(requested.getMaxTokens(), verified.getMaxTokens())) {
            return false;
        }
        if (requested.getReferenceMaxTokens() != null && !Intrinsics.areEqual(requested.getReferenceMaxTokens(), verified.getReferenceMaxTokens())) {
            return false;
        }
        if (requested.getFanout() == null || Intrinsics.areEqual(requested.getFanout(), verified.getFanout())) {
            return requested.getEnabled() == null || Intrinsics.areEqual(requested.getEnabled(), verified.getEnabled());
        }
        return false;
    }
}
