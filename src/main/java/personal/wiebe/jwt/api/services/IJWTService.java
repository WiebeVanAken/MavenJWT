package personal.wiebe.jwt.api.services;

import personal.wiebe.jwt.api.coders.ITokenDecoder;
import personal.wiebe.jwt.api.coders.ITokenEncoder;
import personal.wiebe.jwt.api.principals.BasePrincipals;

/**
 * A simple service to expose the JWT token encoder / decoder API
 */
public interface IJWTService {
    /**
     * Encode a UserClaimsPrincipal to a JWT token
     *
     * @param userClaimsData The data to encode.
     * @return An encoded string using the JWT format.
     * @throws UnsupportedOperationException When an internal encoding exception occurs.
     */
    String encodeToken(BasePrincipals userClaimsData) throws UnsupportedOperationException;

    /**
     * Decode a JWT token to a UserClaimsPrincipal
     *
     * @param token The token to decrypt
     * @return A decoded token in the form of the UserClaimsPrincipal
     * @throws UnsupportedOperationException When an internal decoding exception occurs
     */
    BasePrincipals decodeToken(String token) throws UnsupportedOperationException;

    /**
     * Set the encoder to use for encoding tokens.
     *
     * @param tokenEncoder The encoder to use for encoding tokens.
     */
    void setTokenEncoder(ITokenEncoder tokenEncoder);

    /**
     * Set the decoder to use for decoding tokens.
     *
     * @param tokenDecoder The decoder to use for decoding tokens.
     */
    void setTokenDecoder(ITokenDecoder tokenDecoder);
}
