package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes14.dex */
@CheckReturnValue
interface MessageInfo {
    MessageLite getDefaultInstance();

    ProtoSyntax getSyntax();

    boolean isMessageSetWireFormat();
}
