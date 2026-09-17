package ai.nous.aurora;

import ai.nous.aurora.data.local.db.AuroraDatabase;
import ai.nous.aurora.data.remote.GatewayClient;
import ai.nous.aurora.data.repository.DefaultSessionRepository;
import ai.nous.aurora.data.repository.DefaultSettingsRepository;
import ai.nous.aurora.data.repository.SessionRepository;
import ai.nous.aurora.data.repository.SettingsRepository;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import ai.nous.aurora.service.AuroraNotificationManager;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppModule.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Lai/nous/aurora/AppModule;", "", "context", "Landroid/content/Context;", "isDebug", "", "<init>", "(Landroid/content/Context;Z)V", "appContext", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "credentialStore", "Lai/nous/aurora/data/storage/AuroraCredentialStore;", "getCredentialStore", "()Lai/nous/aurora/data/storage/AuroraCredentialStore;", "database", "Lai/nous/aurora/data/local/db/AuroraDatabase;", "getDatabase", "()Lai/nous/aurora/data/local/db/AuroraDatabase;", "gatewayClient", "Lai/nous/aurora/data/remote/GatewayClient;", "getGatewayClient", "()Lai/nous/aurora/data/remote/GatewayClient;", "sessionRepository", "Lai/nous/aurora/data/repository/SessionRepository;", "getSessionRepository", "()Lai/nous/aurora/data/repository/SessionRepository;", "settingsRepository", "Lai/nous/aurora/data/repository/SettingsRepository;", "getSettingsRepository", "()Lai/nous/aurora/data/repository/SettingsRepository;", "notifications", "Lai/nous/aurora/service/AuroraNotificationManager;", "getNotifications", "()Lai/nous/aurora/service/AuroraNotificationManager;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AppModule {
    public static final int $stable = 8;
    private final Context appContext;
    private final AuroraCredentialStore credentialStore;
    private final AuroraDatabase database;
    private final GatewayClient gatewayClient;
    private final AuroraNotificationManager notifications;
    private final SessionRepository sessionRepository;
    private final SettingsRepository settingsRepository;

    /* JADX WARN: Multi-variable type inference failed */
    public AppModule(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.appContext = context.getApplicationContext();
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.credentialStore = new AuroraCredentialStore(appContext);
        AuroraDatabase.Companion companion = AuroraDatabase.INSTANCE;
        Context appContext2 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
        this.database = companion.getInstance(appContext2);
        this.gatewayClient = new GatewayClient(z, null, 2, null);
        this.sessionRepository = new DefaultSessionRepository(this.database, this.gatewayClient, this.credentialStore);
        this.settingsRepository = new DefaultSettingsRepository(context, this.credentialStore, null, 4, null);
        Context appContext3 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext3, "appContext");
        this.notifications = new AuroraNotificationManager(appContext3);
    }

    public /* synthetic */ AppModule(Context context, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? true : z);
    }

    public final AuroraCredentialStore getCredentialStore() {
        return this.credentialStore;
    }

    public final AuroraDatabase getDatabase() {
        return this.database;
    }

    public final GatewayClient getGatewayClient() {
        return this.gatewayClient;
    }

    public final SessionRepository getSessionRepository() {
        return this.sessionRepository;
    }

    public final SettingsRepository getSettingsRepository() {
        return this.settingsRepository;
    }

    public final AuroraNotificationManager getNotifications() {
        return this.notifications;
    }
}
