package personal.wiebe.jwt.core.coders;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import personal.wiebe.jwt.api.coders.ITokenEncoder;
import personal.wiebe.jwt.api.principals.BasePrincipals;
import personal.wiebe.jwt.api.providers.ISecretKeyProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Default
class TokenEncoder implements ITokenEncoder {
    private static final String headerJson = "{\"alg\": \"HS256\", \"typ\": \"JWT\"}";
    private ISecretKeyProvider secretKeyProvider;

    /**
     * Encode a UserClaimsPrincipal to a JWT token.
     *
     * @param basePrincipals The data to encode.
     * @return An encoded string using the JWT format.
     * @throws IOException When the secret key cannot be provided.
     */
    @Override
    public final String encode(BasePrincipals basePrincipals) throws IOException {
        var jsonBuilder = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
        var payloadJson = jsonBuilder.toJson(basePrincipals);
        var secretKey = secretKeyProvider.provide();

        return String.format("%s.%s.%s",
            Base64.encodeBase64String(headerJson.getBytes(StandardCharsets.UTF_8)),
            Base64.encodeBase64String(payloadJson.getBytes(StandardCharsets.UTF_8)),
            DigestUtils.sha256Hex(String.format("%s.%s.%s", headerJson, payloadJson, secretKey))
        );
    }

    @Inject
    public void setSecretKeyProvider(ISecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }
}
