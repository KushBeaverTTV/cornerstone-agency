package ai.nous.aurora;

import ai.nous.aurora.data.remote.GatewayClient;
import ai.nous.aurora.data.repository.SessionRepository;
import ai.nous.aurora.data.repository.SettingsRepository;
import ai.nous.aurora.data.storage.AuroraCredentialStore;
import ai.nous.aurora.service.AuroraNotificationManager;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ%\u0010\u000e\u001a\u0002H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0012H\u0016¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lai/nous/aurora/MainViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "credentialStore", "Lai/nous/aurora/data/storage/AuroraCredentialStore;", "gatewayClient", "Lai/nous/aurora/data/remote/GatewayClient;", "sessionRepository", "Lai/nous/aurora/data/repository/SessionRepository;", "settingsRepository", "Lai/nous/aurora/data/repository/SettingsRepository;", "notifications", "Lai/nous/aurora/service/AuroraNotificationManager;", "<init>", "(Lai/nous/aurora/data/storage/AuroraCredentialStore;Lai/nous/aurora/data/remote/GatewayClient;Lai/nous/aurora/data/repository/SessionRepository;Lai/nous/aurora/data/repository/SettingsRepository;Lai/nous/aurora/service/AuroraNotificationManager;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MainViewModelFactory implements ViewModelProvider.Factory {
    public static final int $stable = 8;
    private final AuroraCredentialStore credentialStore;
    private final GatewayClient gatewayClient;
    private final AuroraNotificationManager notifications;
    private final SessionRepository sessionRepository;
    private final SettingsRepository settingsRepository;

    public MainViewModelFactory(AuroraCredentialStore credentialStore, GatewayClient gatewayClient, SessionRepository sessionRepository, SettingsRepository settingsRepository, AuroraNotificationManager notifications) {
        Intrinsics.checkNotNullParameter(credentialStore, "credentialStore");
        Intrinsics.checkNotNullParameter(gatewayClient, "gatewayClient");
        Intrinsics.checkNotNullParameter(sessionRepository, "sessionRepository");
        Intrinsics.checkNotNullParameter(settingsRepository, "settingsRepository");
        this.credentialStore = credentialStore;
        this.gatewayClient = gatewayClient;
        this.sessionRepository = sessionRepository;
        this.settingsRepository = settingsRepository;
        this.notifications = notifications;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MainViewModelFactory(AuroraCredentialStore auroraCredentialStore, GatewayClient gatewayClient, SessionRepository sessionRepository, SettingsRepository settingsRepository, AuroraNotificationManager auroraNotificationManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(auroraCredentialStore, gatewayClient, sessionRepository, settingsRepository, (i & 16) == 0 ? auroraNotificationManager : null);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return new MainViewModel(this.credentialStore, this.gatewayClient, this.sessionRepository, this.settingsRepository, this.notifications);
        }
        throw new IllegalStateException(("Unknown ViewModel: " + modelClass.getName()).toString());
    }
}
