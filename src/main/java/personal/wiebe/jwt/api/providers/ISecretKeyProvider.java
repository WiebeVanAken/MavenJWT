package personal.wiebe.jwt.api.providers;

import java.io.IOException;

public interface ISecretKeyProvider {
    /**
     * Provide a secret key used for token en/de-coding.
     *
     * @return A secret key string.
     * @throws IOException When an internal IO exception occurs
     */
    String provide() throws IOException;
}
