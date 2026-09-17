package ai.nous.aurora.data.remote;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/* JADX INFO: compiled from: HostVoiceClient.kt */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lai/nous/aurora/data/remote/StageReportingRequestBody;", "Lokhttp3/RequestBody;", "delegate", "onStage", "Lkotlin/Function1;", "Lai/nous/aurora/data/remote/HostVoiceClient$Stage;", "", "<init>", "(Lokhttp3/RequestBody;Lkotlin/jvm/functions/Function1;)V", "contentType", "Lokhttp3/MediaType;", "contentLength", "", "writeTo", "sink", "Lokio/BufferedSink;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StageReportingRequestBody extends RequestBody {
    public static final int $stable = 8;
    private final RequestBody delegate;
    private final Function1<HostVoiceClient.Stage, Unit> onStage;

    /* JADX WARN: Multi-variable type inference failed */
    public StageReportingRequestBody(RequestBody delegate, Function1<? super HostVoiceClient.Stage, Unit> onStage) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(onStage, "onStage");
        this.delegate = delegate;
        this.onStage = onStage;
    }

    @Override // okhttp3.RequestBody
    /* JADX INFO: renamed from: contentType */
    public MediaType getContentType() {
        return this.delegate.getContentType();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        return this.delegate.contentLength();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.delegate.writeTo(sink);
        this.onStage.invoke(HostVoiceClient.Stage.PROCESSING);
    }
}
