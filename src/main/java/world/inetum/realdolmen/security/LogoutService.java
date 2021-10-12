package world.inetum.realdolmen.security;

import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Model
public class LogoutService {

    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/index?faces-redirect=true";
    }
}
