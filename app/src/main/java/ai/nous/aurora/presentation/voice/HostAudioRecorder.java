package ai.nous.aurora.presentation.voice;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Build;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HostAudioRecorder.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lai/nous/aurora/presentation/voice/HostAudioRecorder;", "", "outputFile", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "recorder", "Landroid/media/MediaRecorder;", "start", "", "context", "Landroid/content/Context;", "stop", "cancel", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HostAudioRecorder {
    public static final int $stable = 8;
    private final File outputFile;
    private MediaRecorder recorder;

    public HostAudioRecorder(File outputFile) {
        Intrinsics.checkNotNullParameter(outputFile, "outputFile");
        this.outputFile = outputFile;
    }

    public final void start(Context context) throws IOException {
        MediaRecorder r;
        Intrinsics.checkNotNullParameter(context, "context");
        File parentFile = this.outputFile.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
        }
        if (this.outputFile.exists()) {
            this.outputFile.delete();
        }
        if (Build.VERSION.SDK_INT >= 31) {
            r = new MediaRecorder(context);
        } else {
            r = new MediaRecorder();
        }
        r.setAudioSource(6);
        r.setOutputFormat(2);
        r.setAudioEncoder(3);
        r.setAudioSamplingRate(16000);
        r.setAudioEncodingBitRate(64000);
        r.setAudioChannels(1);
        r.setOutputFile(this.outputFile.getAbsolutePath());
        r.prepare();
        r.start();
        this.recorder = r;
    }

    public final File stop() {
        MediaRecorder r = this.recorder;
        if (r == null) {
            throw new IllegalStateException("not recording".toString());
        }
        try {
            r.stop();
        } catch (Exception e) {
        }
        try {
            r.release();
        } catch (Exception e2) {
        }
        this.recorder = null;
        return this.outputFile;
    }

    public final void cancel() {
        try {
            MediaRecorder mediaRecorder = this.recorder;
            if (mediaRecorder != null) {
                mediaRecorder.reset();
            }
            MediaRecorder mediaRecorder2 = this.recorder;
            if (mediaRecorder2 != null) {
                mediaRecorder2.release();
            }
        } catch (Exception e) {
        }
        this.recorder = null;
        try {
            Result.Companion companion = Result.Companion;
            HostAudioRecorder $this$cancel_u24lambda_u240 = this;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(Boolean.valueOf($this$cancel_u24lambda_u240.outputFile.delete()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            ai.nous.aurora.compat.Compat.m7388constructorimpl(ResultKt.createFailure(th));
        }
    }
}
