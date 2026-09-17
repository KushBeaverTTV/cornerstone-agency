package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okio.BufferedSink;
import okio.Okio;

/* JADX INFO: compiled from: CallServerInterceptor.kt */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", "code", "", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:112:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x00ee A[Catch: IOException -> 0x01de, TRY_LEAVE, TryCatch #6 {IOException -> 0x01de, blocks: (B:41:0x00d7, B:43:0x00ee), top: B:110:0x00d7 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x013e  */
    /* JADX WARN: Code duplicated, block: B:64:0x015d A[Catch: IOException -> 0x01da, TryCatch #1 {IOException -> 0x01da, blocks: (B:59:0x0143, B:63:0x014e, B:65:0x016d, B:67:0x017e, B:74:0x0195, B:76:0x019b, B:80:0x01a8, B:82:0x01c5, B:83:0x01cd, B:84:0x01d8, B:69:0x018a, B:64:0x015d), top: B:100:0x0143 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x018a A[Catch: IOException -> 0x01da, TryCatch #1 {IOException -> 0x01da, blocks: (B:59:0x0143, B:63:0x014e, B:65:0x016d, B:67:0x017e, B:74:0x0195, B:76:0x019b, B:80:0x01a8, B:82:0x01c5, B:83:0x01cd, B:84:0x01d8, B:69:0x018a, B:64:0x015d), top: B:100:0x0143 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x0195 A[Catch: IOException -> 0x01da, TryCatch #1 {IOException -> 0x01da, blocks: (B:59:0x0143, B:63:0x014e, B:65:0x016d, B:67:0x017e, B:74:0x0195, B:76:0x019b, B:80:0x01a8, B:82:0x01c5, B:83:0x01cd, B:84:0x01d8, B:69:0x018a, B:64:0x015d), top: B:100:0x0143 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x019b A[Catch: IOException -> 0x01da, TryCatch #1 {IOException -> 0x01da, blocks: (B:59:0x0143, B:63:0x014e, B:65:0x016d, B:67:0x017e, B:74:0x0195, B:76:0x019b, B:80:0x01a8, B:82:0x01c5, B:83:0x01cd, B:84:0x01d8, B:69:0x018a, B:64:0x015d), top: B:100:0x0143 }] */
    /* JADX WARN: Code duplicated, block: B:77:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:80:0x01a8 A[Catch: IOException -> 0x01da, TryCatch #1 {IOException -> 0x01da, blocks: (B:59:0x0143, B:63:0x014e, B:65:0x016d, B:67:0x017e, B:74:0x0195, B:76:0x019b, B:80:0x01a8, B:82:0x01c5, B:83:0x01cd, B:84:0x01d8, B:69:0x018a, B:64:0x015d), top: B:100:0x0143 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x01c5 A[Catch: IOException -> 0x01da, TryCatch #1 {IOException -> 0x01da, blocks: (B:59:0x0143, B:63:0x014e, B:65:0x016d, B:67:0x017e, B:74:0x0195, B:76:0x019b, B:80:0x01a8, B:82:0x01c5, B:83:0x01cd, B:84:0x01d8, B:69:0x018a, B:64:0x015d), top: B:100:0x0143 }] */
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        IOException sendRequestException;
        Response.Builder responseBuilder;
        Response response;
        int code;
        Response responseBuild;
        Response response2;
        ResponseBody responseBodyBody;
        long contentLength;
        Response.Builder responseBuilder2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Exchange exchange = realChain.getExchange();
        Intrinsics.checkNotNull(exchange);
        Request request = realChain.getRequest();
        RequestBody requestBody = request.body();
        long sentRequestMillis = System.currentTimeMillis();
        boolean invokeStartEvent = true;
        Response.Builder responseBuilder3 = null;
        IOException sendRequestException2 = null;
        try {
            exchange.writeRequestHeaders(request);
            if (!HttpMethod.permitsRequestBody(request.method()) || requestBody == null) {
                exchange.noRequestBody();
            } else {
                if (StringsKt.equals("100-continue", request.header("Expect"), true)) {
                    exchange.flushRequest();
                    responseBuilder3 = exchange.readResponseHeaders(true);
                    exchange.responseHeadersStart();
                    invokeStartEvent = false;
                }
                if (responseBuilder3 != null) {
                    exchange.noRequestBody();
                    if (!exchange.getConnection().isMultiplexed$okhttp()) {
                        exchange.noNewExchangesOnConnection();
                    }
                } else if (requestBody.isDuplex()) {
                    exchange.flushRequest();
                    requestBody.writeTo(Okio.buffer(exchange.createRequestBody(request, true)));
                } else {
                    BufferedSink bufferedRequestBody = Okio.buffer(exchange.createRequestBody(request, false));
                    requestBody.writeTo(bufferedRequestBody);
                    bufferedRequestBody.close();
                }
            }
            if (requestBody == null || !requestBody.isDuplex()) {
                exchange.finishRequest();
            }
        } catch (IOException e) {
            if ((e instanceof ConnectionShutdownException) || !exchange.getHasFailure()) {
                throw e;
            }
            sendRequestException2 = e;
        }
        if (responseBuilder3 != null) {
            responseBuilder = responseBuilder3;
            sendRequestException = sendRequestException2;
            response = responseBuilder3.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            code = response.code();
            if (shouldIgnoreAndWaitForRealResponse(code)) {
                responseBuilder2 = exchange.readResponseHeaders(false);
                Intrinsics.checkNotNull(responseBuilder2);
                if (invokeStartEvent) {
                    exchange.responseHeadersStart();
                    response = responseBuilder2.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                    code = response.code();
                    responseBuilder = responseBuilder2;
                } else {
                    response = responseBuilder2.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                    code = response.code();
                    responseBuilder = responseBuilder2;
                }
            }
            exchange.responseHeadersEnd(response);
            if (this.forWebSocket) {
                responseBuild = response.newBuilder().body(exchange.openResponseBody(response)).build();
            } else {
                responseBuild = response.newBuilder().body(exchange.openResponseBody(response)).build();
            }
            response2 = responseBuild;
            if (StringsKt.equals("close", response2.request().header("Connection"), true)) {
                exchange.noNewExchangesOnConnection();
            } else {
                exchange.noNewExchangesOnConnection();
            }
            if (code != 204) {
                responseBodyBody = response2.body();
                if (responseBodyBody != null) {
                    contentLength = responseBodyBody.getContentLength();
                } else {
                    contentLength = -1;
                }
                if (contentLength > 0) {
                    StringBuilder sbAppend = new StringBuilder().append("HTTP ").append(code).append(" had non-zero Content-Length: ");
                    ResponseBody responseBodyBody2 = response2.body();
                    throw new ProtocolException(sbAppend.append(responseBodyBody2 != null ? Long.valueOf(responseBodyBody2.getContentLength()) : null).toString());
                }
            } else {
                responseBodyBody = response2.body();
                if (responseBodyBody != null) {
                    contentLength = responseBodyBody.getContentLength();
                } else {
                    contentLength = -1;
                }
                if (contentLength > 0) {
                    StringBuilder sbAppend2 = new StringBuilder().append("HTTP ").append(code).append(" had non-zero Content-Length: ");
                    ResponseBody responseBodyBody3 = response2.body();
                    throw new ProtocolException(sbAppend2.append(responseBodyBody3 != null ? Long.valueOf(responseBodyBody3.getContentLength()) : null).toString());
                }
            }
            return response2;
        }
        try {
            Response.Builder responseHeaders = exchange.readResponseHeaders(false);
            Intrinsics.checkNotNull(responseHeaders);
            responseBuilder3 = responseHeaders;
            if (invokeStartEvent) {
                exchange.responseHeadersStart();
                invokeStartEvent = false;
            }
            try {
                responseBuilder = responseBuilder3;
                sendRequestException = sendRequestException2;
                try {
                    response = responseBuilder3.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                    code = response.code();
                    if (shouldIgnoreAndWaitForRealResponse(code)) {
                        responseBuilder2 = exchange.readResponseHeaders(false);
                        Intrinsics.checkNotNull(responseBuilder2);
                        if (invokeStartEvent) {
                            try {
                                exchange.responseHeadersStart();
                                try {
                                    try {
                                        response = responseBuilder2.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                                        code = response.code();
                                        responseBuilder = responseBuilder2;
                                    } catch (IOException e2) {
                                        e = e2;
                                    }
                                } catch (IOException e3) {
                                    e = e3;
                                }
                            } catch (IOException e4) {
                                e = e4;
                            }
                        } else {
                            response = responseBuilder2.request(request).handshake(exchange.getConnection().getHandshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                            code = response.code();
                            responseBuilder = responseBuilder2;
                        }
                    }
                    try {
                        exchange.responseHeadersEnd(response);
                        if (this.forWebSocket || code != 101) {
                            responseBuild = response.newBuilder().body(exchange.openResponseBody(response)).build();
                        } else {
                            responseBuild = response.newBuilder().body(Util.EMPTY_RESPONSE).build();
                        }
                        response2 = responseBuild;
                        if (StringsKt.equals("close", response2.request().header("Connection"), true) || StringsKt.equals("close", Response.header$default(response2, "Connection", null, 2, null), true)) {
                            exchange.noNewExchangesOnConnection();
                        }
                        if (code != 204 || code == 205) {
                            responseBodyBody = response2.body();
                            if (responseBodyBody != null) {
                                contentLength = responseBodyBody.getContentLength();
                            } else {
                                contentLength = -1;
                            }
                            if (contentLength > 0) {
                                StringBuilder sbAppend3 = new StringBuilder().append("HTTP ").append(code).append(" had non-zero Content-Length: ");
                                ResponseBody responseBodyBody4 = response2.body();
                                throw new ProtocolException(sbAppend3.append(responseBodyBody4 != null ? Long.valueOf(responseBodyBody4.getContentLength()) : null).toString());
                            }
                        }
                        return response2;
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                e = e7;
                sendRequestException = sendRequestException2;
            }
        } catch (IOException e8) {
            e = e8;
            sendRequestException = sendRequestException2;
        }
        if (sendRequestException == null) {
            throw e;
        }
        ExceptionsKt.addSuppressed(sendRequestException, e);
        throw sendRequestException;
    }

    private final boolean shouldIgnoreAndWaitForRealResponse(int code) {
        if (code == 100) {
            return true;
        }
        return 102 <= code && code < 200;
    }
}
