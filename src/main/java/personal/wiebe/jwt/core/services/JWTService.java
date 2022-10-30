package personal.wiebe.jwt.core.services;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.json.stream.JsonParsingException;
import personal.wiebe.jwt.api.coders.ITokenDecoder;
import personal.wiebe.jwt.api.coders.ITokenEncoder;
import personal.wiebe.jwt.api.principals.BasePrincipals;
import personal.wiebe.jwt.api.services.IJWTService;

import java.io.IOException;

@Default
class JWTService implements IJWTService {
    private ITokenEncoder tokenEncoder;
    private ITokenDecoder tokenDecoder;

    @Override
    public String encodeToken(BasePrincipals basePrincipals) {
        try {
            return tokenEncoder.encode(basePrincipals);
        } catch (IOException e) {
            throw new UnsupportedOperationException("The provided token could not be encoded.");
        }
    }

    @Override
    public BasePrincipals decodeToken(String token) {
        try {
            return tokenDecoder.decodeToken(token);
        } catch (JsonParsingException e) {
            throw new UnsupportedOperationException("The provided token could not be parsed using JSON");
        }
    }

    @Override
    @Inject
    public void setTokenEncoder(ITokenEncoder tokenEncoder) {
        this.tokenEncoder = tokenEncoder;
    }

    @Override
    @Inject
    public void setTokenDecoder(ITokenDecoder tokenDecoder) {
        this.tokenDecoder = tokenDecoder;
    }
}
