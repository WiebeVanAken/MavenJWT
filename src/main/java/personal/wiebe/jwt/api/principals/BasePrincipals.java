package personal.wiebe.jwt.api.principals;

import jakarta.json.JsonObject;

import javax.security.auth.Subject;
import java.security.Principal;

public class BasePrincipals implements Principal {
    protected String name;

    public BasePrincipals(String name) {
        this.name = name;
    }
    public BasePrincipals(JsonObject jsonObject) {
        this(jsonObject.getString("name"));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
