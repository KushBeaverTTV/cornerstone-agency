package ai.nous.aurora.data.repository;

import ai.nous.aurora.data.remote.MoaConfigDto;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: SettingsDataContracts.kt */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H¦@¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lai/nous/aurora/data/repository/MoaWriteTransport;", "", "get", "Lai/nous/aurora/data/repository/MoaTransportResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "put", "body", "Lai/nous/aurora/data/remote/MoaConfigDto;", "(Lai/nous/aurora/data/remote/MoaConfigDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MoaWriteTransport {
    Object get(Continuation continuation);

    Object put(MoaConfigDto moaConfigDto, Continuation continuation);
}
