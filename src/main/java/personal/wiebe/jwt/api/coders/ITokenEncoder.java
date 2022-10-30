package personal.wiebe.jwt.api.coders;

import personal.wiebe.jwt.api.principals.BasePrincipals;
import personal.wiebe.jwt.api.providers.ISecretKeyProvider;

import java.io.IOException;

public interface ITokenEncoder {
    /**
     * Encode a UserClaimsPrincipal to a JWT token.
     *
     * @param basePrincipals The data to encode.
     * @return An encoded string using the JWT format.
     * @throws IOException When the secret key cannot be provided.
     */
    String encode(BasePrincipals basePrincipals) throws IOException;

    /**
     * Set the secret key provider used by this token encoder.
     *
     * @param secretKeyProvider The secret key provider to use.
     */
    void setSecretKeyProvider(ISecretKeyProvider secretKeyProvider);
}
