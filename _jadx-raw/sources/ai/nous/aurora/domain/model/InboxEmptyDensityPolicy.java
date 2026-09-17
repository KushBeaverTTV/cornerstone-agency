package ai.nous.aurora.domain.model;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: InboxEmptyDensityPolicy.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\fJ\u0006\u0010\u0010\u001a\u00020\fJ\u0006\u0010\u0011\u001a\u00020\fJ\u0006\u0010\u0012\u001a\u00020\u0005J\u0006\u0010\u0013\u001a\u00020\fJ\u0006\u0010\u0014\u001a\u00020\u0005J\u0006\u0010\u0015\u001a\u00020\u0005J\u0006\u0010\u0016\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/domain/model/InboxEmptyDensityPolicy;", "", "<init>", "()V", "TOP_PADDING_DP", "", "TOP_PADDING_LANDSCAPE_DP", "HORIZONTAL_PADDING_DP", "CTA_MIN_HEIGHT_DP", "placement", "Lai/nous/aurora/domain/model/InboxEmptyDensityPolicy$Placement;", "isLandscape", "", "topPaddingDp", "usesCenterVoid", "ctaUsesPillButton", "useHeaderPillCta", "useCenterGhostTextCta", "ctaMinHeightDp", "ctaEmphasized", "headlineFontSp", "bodyFontSp", "verticalSpacingDp", "Placement", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InboxEmptyDensityPolicy {
    public static final int $stable = 0;
    public static final int CTA_MIN_HEIGHT_DP = 40;
    public static final int HORIZONTAL_PADDING_DP = 24;
    public static final InboxEmptyDensityPolicy INSTANCE = new InboxEmptyDensityPolicy();
    public static final int TOP_PADDING_DP = 48;
    public static final int TOP_PADDING_LANDSCAPE_DP = 24;

    private InboxEmptyDensityPolicy() {
    }

    /* JADX INFO: compiled from: InboxEmptyDensityPolicy.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lai/nous/aurora/domain/model/InboxEmptyDensityPolicy$Placement;", "", "<init>", "(Ljava/lang/String;I)V", "TopWeighted", "Center", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public enum Placement {
        TopWeighted,
        Center;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Placement> getEntries() {
            return $ENTRIES;
        }
    }

    public final Placement placement() {
        return Placement.TopWeighted;
    }

    public final Placement placement(boolean isLandscape) {
        return Placement.TopWeighted;
    }

    public final int topPaddingDp() {
        return 48;
    }

    public final int topPaddingDp(boolean isLandscape) {
        return isLandscape ? 24 : 48;
    }

    public final boolean usesCenterVoid() {
        return placement() == Placement.Center;
    }

    public final boolean usesCenterVoid(boolean isLandscape) {
        return false;
    }

    public final boolean ctaUsesPillButton() {
        return true;
    }

    public final boolean useHeaderPillCta() {
        return ctaUsesPillButton();
    }

    public final boolean useCenterGhostTextCta() {
        return false;
    }

    public final int ctaMinHeightDp() {
        return 40;
    }

    public final boolean ctaEmphasized() {
        return true;
    }

    public final int headlineFontSp() {
        return 22;
    }

    public final int bodyFontSp() {
        return 14;
    }

    public final int verticalSpacingDp() {
        return 16;
    }
}
