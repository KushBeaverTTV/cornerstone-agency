package ai.nous.aurora;

import android.app.Application;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuroraApplication.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lai/nous/aurora/AuroraApplication;", "Landroid/app/Application;", "<init>", "()V", "value", "Lai/nous/aurora/AppModule;", "appModule", "getAppModule", "()Lai/nous/aurora/AppModule;", "onCreate", "", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuroraApplication extends Application {
    private static AuroraApplication instance;
    private AppModule appModule;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public final AppModule getAppModule() {
        AppModule appModule = this.appModule;
        if (appModule != null) {
            return appModule;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appModule");
        return null;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.appModule = new AppModule(this, true);
    }

    /* JADX INFO: compiled from: AuroraApplication.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lai/nous/aurora/AuroraApplication$Companion;", "", "<init>", "()V", "value", "Lai/nous/aurora/AuroraApplication;", "instance", "getInstance", "()Lai/nous/aurora/AuroraApplication;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AuroraApplication getInstance() {
            AuroraApplication auroraApplication = AuroraApplication.instance;
            if (auroraApplication != null) {
                return auroraApplication;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            return null;
        }
    }
}
