package world.inetum.realdolmen.security;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Arrays;
import java.util.HashSet;

@CustomFormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(
        loginPage = "login.xhtml",
        errorPage = "",
        useForwardToLogin = true
))
@ApplicationScoped
public class TRMIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential c = (UsernamePasswordCredential) credential;
            String username = c.getCaller();

            if ("RDM".equals(username)) {
                return new CredentialValidationResult(
                        username,
                        new HashSet<>(Arrays.asList("CONSULTANT"))
                );
            } else if ("Maarten".equals(username)) {
                return new CredentialValidationResult(
                        username,
                        new HashSet<>(Arrays.asList("MANAGER"))
                );
            } else {
                return CredentialValidationResult.INVALID_RESULT;
            }
        } else {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
    }
}
