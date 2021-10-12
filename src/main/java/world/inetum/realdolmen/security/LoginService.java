package world.inetum.realdolmen.security;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

@Model
public class LoginService {

    private String username;

    private String password;

    @Inject
    SecurityContext securityContext;

    public String login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(
                        new UsernamePasswordCredential(username, password)
                )
        );

        switch (status) {
            case NOT_DONE:
                break;
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SUCCESS:
                return "trm?faces-redirect=true";
            case SEND_FAILURE:
                facesContext.addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                getMessage(facesContext, "login.invalid.summary"),
                                getMessage(facesContext, "login.invalid.detail")
                        )
                );
        }

        return null;
    }

    public static String getMessage(FacesContext context, String key) {
        return ResourceBundle.getBundle(
                "messages",
                context.getViewRoot().getLocale()).getString(key);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
