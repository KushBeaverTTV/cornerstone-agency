package com.google.crypto.tink.jwt;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Optional;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes15.dex */
public final class JwkSetConverter {
    private static final String JWT_ECDSA_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    private static final String JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    private static final String JWT_RSA_SSA_PSS_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:31:0x0075  */
    public static String fromPublicKeysetHandle(KeysetHandle handle) throws GeneralSecurityException, IOException {
        JsonArray keys = new JsonArray();
        for (int i = 0; i < handle.size(); i++) {
            KeysetHandle.Entry entry = handle.getAt(i);
            if (entry.getStatus() == KeyStatus.ENABLED) {
                Key publicKey = entry.getKey();
                if (!(publicKey instanceof LegacyProtoKey)) {
                    throw new GeneralSecurityException("only LegacyProtoKey is currently supported");
                }
                LegacyProtoKey protoKey = (LegacyProtoKey) publicKey;
                ProtoKeySerialization protoKeySerialization = protoKey.getSerialization(null);
                if (protoKeySerialization.getOutputPrefixType() != OutputPrefixType.RAW && protoKeySerialization.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("only OutputPrefixType RAW and TINK are supported");
                }
                if (protoKeySerialization.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    throw new GeneralSecurityException("only public keys can be converted");
                }
                switch (protoKeySerialization.getTypeUrl()) {
                    case "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey":
                        keys.add(convertJwtEcdsaKey(protoKeySerialization));
                        break;
                    case "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey":
                        keys.add(convertJwtRsaSsaPkcs1(protoKeySerialization));
                        break;
                    case "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey":
                        keys.add(convertJwtRsaSsaPss(protoKeySerialization));
                        break;
                    default:
                        throw new GeneralSecurityException(String.format("key type %s is not supported", protoKeySerialization.getTypeUrl()));
                }
            }
        }
        JsonObject jwkSet = new JsonObject();
        jwkSet.add("keys", keys);
        return jwkSet.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:18:0x006a  */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0049. Please report as an issue. */
    public static KeysetHandle toPublicKeysetHandle(String jwkSet) throws GeneralSecurityException, IOException {
        ProtoKeySerialization keySerialization;
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(jwkSet));
            jsonReader.setLenient(false);
            JsonObject jsonKeyset = Streams.parse(jsonReader).getAsJsonObject();
            KeysetHandle.Builder builder = KeysetHandle.newBuilder();
            JsonArray jsonKeys = jsonKeyset.get("keys").getAsJsonArray();
            for (JsonElement element : jsonKeys) {
                JsonObject jsonKey = element.getAsJsonObject();
                byte b = 2;
                String algPrefix = getStringItem(jsonKey, "alg").substring(0, 2);
                switch (algPrefix.hashCode()) {
                    case 2222:
                        if (!algPrefix.equals("ES")) {
                            b = -1;
                        }
                        break;
                    case 2563:
                        if (algPrefix.equals("PS")) {
                            b = 1;
                        } else {
                            b = -1;
                        }
                        break;
                    case 2625:
                        if (algPrefix.equals("RS")) {
                            b = 0;
                        } else {
                            b = -1;
                        }
                        break;
                    default:
                        b = -1;
                        break;
                }
                switch (b) {
                    case 0:
                        keySerialization = convertToRsaSsaPkcs1Key(jsonKey);
                        break;
                    case 1:
                        keySerialization = convertToRsaSsaPssKey(jsonKey);
                        break;
                    case 2:
                        keySerialization = convertToEcdsaKey(jsonKey);
                        break;
                    default:
                        throw new GeneralSecurityException("unexpected alg value: " + getStringItem(jsonKey, "alg"));
                }
                builder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(keySerialization, null)).withRandomId());
            }
            if (builder.size() <= 0) {
                throw new GeneralSecurityException("empty keyset");
            }
            builder.getAt(0).makePrimary();
            return builder.build();
        } catch (JsonParseException | IllegalStateException | StackOverflowError ex) {
            throw new GeneralSecurityException("JWK set is invalid JSON", ex);
        }
    }

    private static Optional<String> getKid(@Nullable Integer idRequirement) {
        if (idRequirement == null) {
            return Optional.empty();
        }
        byte[] bigEndianKeyId = ByteBuffer.allocate(4).putInt(idRequirement.intValue()).array();
        return Optional.of(Base64.urlSafeEncode(bigEndianKeyId));
    }

    private static JsonObject convertJwtEcdsaKey(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String alg;
        String crv;
        try {
            JwtEcdsaPublicKey jwtEcdsaPublicKey = JwtEcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            switch (jwtEcdsaPublicKey.getAlgorithm()) {
                case ES256:
                    alg = "ES256";
                    crv = "P-256";
                    break;
                case ES384:
                    alg = "ES384";
                    crv = "P-384";
                    break;
                case ES512:
                    alg = "ES512";
                    crv = "P-521";
                    break;
                default:
                    throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonKey = new JsonObject();
            jsonKey.addProperty("kty", "EC");
            jsonKey.addProperty("crv", crv);
            jsonKey.addProperty("x", Base64.urlSafeEncode(jwtEcdsaPublicKey.getX().toByteArray()));
            jsonKey.addProperty("y", Base64.urlSafeEncode(jwtEcdsaPublicKey.getY().toByteArray()));
            jsonKey.addProperty("use", "sig");
            jsonKey.addProperty("alg", alg);
            JsonArray keyOps = new JsonArray();
            keyOps.add("verify");
            jsonKey.add("key_ops", keyOps);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonKey.addProperty("kid", kid.get());
            } else if (jwtEcdsaPublicKey.hasCustomKid()) {
                jsonKey.addProperty("kid", jwtEcdsaPublicKey.getCustomKid().getValue());
            }
            return jsonKey;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtEcdsaPublicKey proto", e);
        }
    }

    private static JsonObject convertJwtRsaSsaPkcs1(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String alg;
        try {
            JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey = JwtRsaSsaPkcs1PublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            switch (jwtRsaSsaPkcs1PublicKey.getAlgorithm()) {
                case RS256:
                    alg = "RS256";
                    break;
                case RS384:
                    alg = "RS384";
                    break;
                case RS512:
                    alg = "RS512";
                    break;
                default:
                    throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonKey = new JsonObject();
            jsonKey.addProperty("kty", "RSA");
            jsonKey.addProperty("n", Base64.urlSafeEncode(jwtRsaSsaPkcs1PublicKey.getN().toByteArray()));
            jsonKey.addProperty("e", Base64.urlSafeEncode(jwtRsaSsaPkcs1PublicKey.getE().toByteArray()));
            jsonKey.addProperty("use", "sig");
            jsonKey.addProperty("alg", alg);
            JsonArray keyOps = new JsonArray();
            keyOps.add("verify");
            jsonKey.add("key_ops", keyOps);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonKey.addProperty("kid", kid.get());
            } else if (jwtRsaSsaPkcs1PublicKey.hasCustomKid()) {
                jsonKey.addProperty("kid", jwtRsaSsaPkcs1PublicKey.getCustomKid().getValue());
            }
            return jsonKey;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPkcs1PublicKey proto", e);
        }
    }

    private static JsonObject convertJwtRsaSsaPss(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String alg;
        try {
            JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey = JwtRsaSsaPssPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            switch (jwtRsaSsaPssPublicKey.getAlgorithm()) {
                case PS256:
                    alg = "PS256";
                    break;
                case PS384:
                    alg = "PS384";
                    break;
                case PS512:
                    alg = "PS512";
                    break;
                default:
                    throw new GeneralSecurityException("unknown algorithm");
            }
            JsonObject jsonKey = new JsonObject();
            jsonKey.addProperty("kty", "RSA");
            jsonKey.addProperty("n", Base64.urlSafeEncode(jwtRsaSsaPssPublicKey.getN().toByteArray()));
            jsonKey.addProperty("e", Base64.urlSafeEncode(jwtRsaSsaPssPublicKey.getE().toByteArray()));
            jsonKey.addProperty("use", "sig");
            jsonKey.addProperty("alg", alg);
            JsonArray keyOps = new JsonArray();
            keyOps.add("verify");
            jsonKey.add("key_ops", keyOps);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (kid.isPresent()) {
                jsonKey.addProperty("kid", kid.get());
            } else if (jwtRsaSsaPssPublicKey.hasCustomKid()) {
                jsonKey.addProperty("kid", jwtRsaSsaPssPublicKey.getCustomKid().getValue());
            }
            return jsonKey;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPssPublicKey proto", e);
        }
    }

    private static String getStringItem(JsonObject obj, String name) throws GeneralSecurityException {
        if (!obj.has(name)) {
            throw new GeneralSecurityException(name + " not found");
        }
        if (!obj.get(name).isJsonPrimitive() || !obj.get(name).getAsJsonPrimitive().isString()) {
            throw new GeneralSecurityException(name + " is not a string");
        }
        return obj.get(name).getAsString();
    }

    private static void expectStringItem(JsonObject obj, String name, String expectedValue) throws GeneralSecurityException {
        String value = getStringItem(obj, name);
        if (!value.equals(expectedValue)) {
            throw new GeneralSecurityException("unexpected " + name + " value: " + value);
        }
    }

    private static void validateUseIsSig(JsonObject jsonKey) throws GeneralSecurityException {
        if (jsonKey.has("use")) {
            expectStringItem(jsonKey, "use", "sig");
        }
    }

    private static void validateKeyOpsIsVerify(JsonObject jsonKey) throws GeneralSecurityException {
        if (!jsonKey.has("key_ops")) {
            return;
        }
        if (!jsonKey.get("key_ops").isJsonArray()) {
            throw new GeneralSecurityException("key_ops is not an array");
        }
        JsonArray keyOps = jsonKey.get("key_ops").getAsJsonArray();
        if (keyOps.size() != 1) {
            throw new GeneralSecurityException("key_ops must contain exactly one element");
        }
        if (!keyOps.get(0).isJsonPrimitive() || !keyOps.get(0).getAsJsonPrimitive().isString()) {
            throw new GeneralSecurityException("key_ops is not a string");
        }
        if (!keyOps.get(0).getAsString().equals("verify")) {
            throw new GeneralSecurityException("unexpected keyOps value: " + keyOps.get(0).getAsString());
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:14:0x002d  */
    private static ProtoKeySerialization convertToRsaSsaPkcs1Key(JsonObject jsonKey) throws GeneralSecurityException {
        JwtRsaSsaPkcs1Algorithm algorithm;
        switch (getStringItem(jsonKey, "alg")) {
            case "RS256":
                algorithm = JwtRsaSsaPkcs1Algorithm.RS256;
                break;
            case "RS384":
                algorithm = JwtRsaSsaPkcs1Algorithm.RS384;
                break;
            case "RS512":
                algorithm = JwtRsaSsaPkcs1Algorithm.RS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(jsonKey, "alg"));
        }
        if (jsonKey.has("p") || jsonKey.has("q") || jsonKey.has("dp") || jsonKey.has("dq") || jsonKey.has("d") || jsonKey.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(jsonKey, "kty", "RSA");
        validateUseIsSig(jsonKey);
        validateKeyOpsIsVerify(jsonKey);
        JwtRsaSsaPkcs1PublicKey.Builder pkcs1PubKeyBuilder = JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm(algorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "n"))));
        if (jsonKey.has("kid")) {
            pkcs1PubKeyBuilder.setCustomKid((JwtRsaSsaPkcs1PublicKey.CustomKid) JwtRsaSsaPkcs1PublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonKey, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL, ((JwtRsaSsaPkcs1PublicKey) pkcs1PubKeyBuilder.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:14:0x002d  */
    private static ProtoKeySerialization convertToRsaSsaPssKey(JsonObject jsonKey) throws GeneralSecurityException {
        JwtRsaSsaPssAlgorithm algorithm;
        switch (getStringItem(jsonKey, "alg")) {
            case "PS256":
                algorithm = JwtRsaSsaPssAlgorithm.PS256;
                break;
            case "PS384":
                algorithm = JwtRsaSsaPssAlgorithm.PS384;
                break;
            case "PS512":
                algorithm = JwtRsaSsaPssAlgorithm.PS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(jsonKey, "alg"));
        }
        if (jsonKey.has("p") || jsonKey.has("q") || jsonKey.has("dq") || jsonKey.has("dq") || jsonKey.has("d") || jsonKey.has("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(jsonKey, "kty", "RSA");
        validateUseIsSig(jsonKey);
        validateKeyOpsIsVerify(jsonKey);
        JwtRsaSsaPssPublicKey.Builder pssPubKeyBuilder = JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm(algorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "n"))));
        if (jsonKey.has("kid")) {
            pssPubKeyBuilder.setCustomKid((JwtRsaSsaPssPublicKey.CustomKid) JwtRsaSsaPssPublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonKey, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PSS_PUBLIC_KEY_URL, ((JwtRsaSsaPssPublicKey) pssPubKeyBuilder.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:14:0x002d  */
    private static ProtoKeySerialization convertToEcdsaKey(JsonObject jsonKey) throws GeneralSecurityException {
        JwtEcdsaAlgorithm algorithm;
        switch (getStringItem(jsonKey, "alg")) {
            case "ES256":
                expectStringItem(jsonKey, "crv", "P-256");
                algorithm = JwtEcdsaAlgorithm.ES256;
                break;
            case "ES384":
                expectStringItem(jsonKey, "crv", "P-384");
                algorithm = JwtEcdsaAlgorithm.ES384;
                break;
            case "ES512":
                expectStringItem(jsonKey, "crv", "P-521");
                algorithm = JwtEcdsaAlgorithm.ES512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Ecdsa Algorithm: " + getStringItem(jsonKey, "alg"));
        }
        if (jsonKey.has("d")) {
            throw new UnsupportedOperationException("importing ECDSA private keys is not implemented");
        }
        expectStringItem(jsonKey, "kty", "EC");
        validateUseIsSig(jsonKey);
        validateKeyOpsIsVerify(jsonKey);
        JwtEcdsaPublicKey.Builder ecdsaPubKeyBuilder = JwtEcdsaPublicKey.newBuilder().setVersion(0).setAlgorithm(algorithm).setX(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "x")))).setY(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(jsonKey, "y"))));
        if (jsonKey.has("kid")) {
            ecdsaPubKeyBuilder.setCustomKid((JwtEcdsaPublicKey.CustomKid) JwtEcdsaPublicKey.CustomKid.newBuilder().setValue(getStringItem(jsonKey, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_ECDSA_PUBLIC_KEY_URL, ((JwtEcdsaPublicKey) ecdsaPubKeyBuilder.build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    @Deprecated
    public static String fromKeysetHandle(KeysetHandle handle, KeyAccess keyAccess) throws GeneralSecurityException, IOException {
        return fromPublicKeysetHandle(handle);
    }

    @Deprecated
    public static KeysetHandle toKeysetHandle(String jwkSet, KeyAccess keyAccess) throws GeneralSecurityException, IOException {
        return toPublicKeysetHandle(jwkSet);
    }

    private JwkSetConverter() {
    }
}
