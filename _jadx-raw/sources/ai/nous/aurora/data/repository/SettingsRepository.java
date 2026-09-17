package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.MoaConfigDto;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlinx.serialization.json.JsonElement;

/* JADX INFO: compiled from: SettingsRepository.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\n\u001a\u00020\u0005H¦@¢\u0006\u0004\b\u000b\u0010\u0006J&\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H¦@¢\u0006\u0004\b\u0011\u0010\u0012J0\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0018\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00100\u00160\u0015H¦@¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006J\u001e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\b2\u0006\u0010\u001c\u001a\u00020\u001dH¦@¢\u0006\u0004\b\u001e\u0010\u001fJ*\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010!\u001a\u00020\u000e2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u000eH¦@¢\u0006\u0004\b#\u0010$J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00152\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u0003H&J\n\u0010)\u001a\u0004\u0018\u00010\u0003H&¨\u0006*"}, d2 = {"Lai/nous/aurora/data/repository/SettingsRepository;", "", "load", "Lai/nous/aurora/data/repository/HermesSettingsSnapshot;", "forceRefresh", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadModelOptions", "Lkotlin/Result;", "Lai/nous/aurora/data/repository/ModelOptionsSnapshot;", "refresh", "loadModelOptions-gIAlu-s", "setValue", "path", "", "value", "Lkotlinx/serialization/json/JsonElement;", "setValue-0E7RQCE", "(Ljava/lang/String;Lkotlinx/serialization/json/JsonElement;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setValues", "pairs", "", "Lkotlin/Pair;", "setValues-gIAlu-s", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadMoa", "Lai/nous/aurora/data/repository/MoaSnapshot;", "saveMoa", "body", "Lai/nous/aurora/data/remote/MoaConfigDto;", "saveMoa-gIAlu-s", "(Lai/nous/aurora/data/remote/MoaConfigDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "encodeMoaPrompt", "prompt", "preset", "encodeMoaPrompt-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "Lai/nous/aurora/data/repository/SettingsSearchIndex$Hit;", "query", "snapshot", "peekCached", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SettingsRepository {
    /* JADX INFO: renamed from: encodeMoaPrompt-0E7RQCE */
    Object mo57encodeMoaPrompt0E7RQCE(String str, String str2, Continuation<? super Result<String>> continuation);

    Object load(boolean z, Continuation<? super HermesSettingsSnapshot> continuation);

    Object loadMoa(boolean z, Continuation<? super MoaSnapshot> continuation);

    /* JADX INFO: renamed from: loadModelOptions-gIAlu-s */
    Object mo58loadModelOptionsgIAlus(boolean z, Continuation<? super Result<ModelOptionsSnapshot>> continuation);

    HermesSettingsSnapshot peekCached();

    /* JADX INFO: renamed from: saveMoa-gIAlu-s */
    Object mo59saveMoagIAlus(MoaConfigDto moaConfigDto, Continuation<? super Result<MoaSnapshot>> continuation);

    List<SettingsSearchIndex.Hit> search(String query, HermesSettingsSnapshot snapshot);

    /* JADX INFO: renamed from: setValue-0E7RQCE */
    Object mo60setValue0E7RQCE(String str, JsonElement jsonElement, Continuation<? super Result<HermesSettingsSnapshot>> continuation);

    /* JADX INFO: renamed from: setValues-gIAlu-s */
    Object mo61setValuesgIAlus(List<? extends Pair<String, ? extends JsonElement>> list, Continuation<? super Result<HermesSettingsSnapshot>> continuation);

    /* JADX INFO: compiled from: SettingsRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object load$default(SettingsRepository settingsRepository, boolean z, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: load");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return settingsRepository.load(z, continuation);
        }

        /* JADX INFO: renamed from: loadModelOptions-gIAlu-s$default, reason: not valid java name */
        public static /* synthetic */ Object m66loadModelOptionsgIAlus$default(SettingsRepository settingsRepository, boolean z, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadModelOptions-gIAlu-s");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return settingsRepository.mo58loadModelOptionsgIAlus(z, continuation);
        }

        public static /* synthetic */ Object loadMoa$default(SettingsRepository settingsRepository, boolean z, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadMoa");
            }
            if ((i & 1) != 0) {
                z = false;
            }
            return settingsRepository.loadMoa(z, continuation);
        }

        /* JADX INFO: renamed from: encodeMoaPrompt-0E7RQCE$default, reason: not valid java name */
        public static /* synthetic */ Object m65encodeMoaPrompt0E7RQCE$default(SettingsRepository settingsRepository, String str, String str2, Continuation continuation, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: encodeMoaPrompt-0E7RQCE");
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            return settingsRepository.mo57encodeMoaPrompt0E7RQCE(str, str2, continuation);
        }
    }
}
