package world.inetum.realdolmen.security;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.NotAuthorizedException;
import java.security.Principal;

@Stateless
public class CurrentUserService {

    @Inject
    SecurityContext securityContext;

    public String getCurrentUserName() {
        Principal principal = securityContext.getCallerPrincipal();
        if (principal != null) {
            String name = principal.getName();
            if (name != null) {
                return name;
            }
        }
        throw new NotAuthorizedException("No-one logged in");
    }

}
