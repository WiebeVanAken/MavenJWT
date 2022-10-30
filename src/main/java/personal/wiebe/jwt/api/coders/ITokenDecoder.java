package personal.wiebe.jwt.api.coders;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParsingException;
import org.apache.commons.codec.binary.Base64;
import personal.wiebe.jwt.api.principals.BasePrincipals;

import java.io.StringReader;

public interface ITokenDecoder {
    /**
     * Decode a token using SHA256 encoding.
     * Must be overridden when implementing a custom tokendecoder.
     *
     * @param token The encoded SHA256 token
     * @return A decoded token in the form of the UserClaimsPrincipal
     * @throws JsonParsingException if the provided decoded token couldn't be parsed using JSON
     * @throws UnsupportedOperationException if the token was not SHA256 encoded
     */
    BasePrincipals decodeToken(String token) throws JsonParsingException, UnsupportedOperationException;

    /**
     * Retrieve the encrypted header section of the JWT token.
     *
     * @param token The encrypted JWT token
     * @return The encrypted header section
     */
    default String retrieveHeader(String token) {
        var firstDelimiter = token.indexOf(".");

        return token.substring(0, firstDelimiter);
    }

    /**
     * Retrieve the encrypted payload section of the JWT token
     *
     * @param token The encrypted JWT token
     * @return The encrypted payload section
     */
    default String retrievePayload(String token) {
        var firstDelimiter = token.indexOf(".");
        var lastDelimiter = token.indexOf(".", firstDelimiter + 1);
        return token.substring(firstDelimiter + 1, lastDelimiter);
    }

    /**
     * Convert the provided payload to a JsonObject
     *
     * @param payload The decrypted payload to convert.
     * @return A JsonObject representation of the payload.
     */
    default JsonObject parseObject(String payload) {
        var decodedBytes = Base64.decodeBase64(payload);
        var decodedPayloadString = new String(decodedBytes);

        var jsonReader = Json.createReader(new StringReader(decodedPayloadString));

        return jsonReader.readObject();
    }


}
