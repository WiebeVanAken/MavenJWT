package personal.wiebe.jwt.core.coders;

import jakarta.enterprise.inject.Default;
import jakarta.json.stream.JsonParsingException;
import personal.wiebe.jwt.api.coders.ITokenDecoder;
import personal.wiebe.jwt.api.principals.BasePrincipals;

/**
 * A base Token Decoder, open to extension if this decoder does not suffice.
 * Not to be used directly, use the IJWTService.
 */
@Default
class TokenDecoder implements ITokenDecoder {
    /**
     * Decode a token using SHA256 encoding.
     * Must be overridden when implementing a custom tokendecoder.
     *
     * @param token The encoded SHA256 token
     * @return A decoded token in the form of the UserClaimsPrincipal
     * @throws JsonParsingException          if the provided decoded token couldn't be parsed using JSON
     * @throws UnsupportedOperationException if the token was not SHA256 encoded
     */
    public BasePrincipals decodeToken(String token) throws JsonParsingException, UnsupportedOperationException {
        if (!validateHeader(token))
            throw new UnsupportedOperationException("The provided token was not sha256 encrypted");

        var payload = retrievePayload(token);
        var payloadAsJsonObject = parseObject(payload);

        return new BasePrincipals(payloadAsJsonObject);
    }

    private boolean validateHeader(String token) {
        var header = retrieveHeader(token);

        var object = parseObject(header);

        return object.getString("alg").equals("HS256")
                && object.getString("typ").equals("JWT");
    }
}
