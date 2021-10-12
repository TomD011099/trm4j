package world.inetum.realdolmen.locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class LanguageService implements Serializable {

    private Locale locale = Locale.ENGLISH;

    public Locale getLocale() {
        return locale;
    }

    public String getLanguageTag() {
        return locale.toLanguageTag();
    }

    public void setLanguageTag(String localeTag) {
        this.locale = Locale.forLanguageTag(localeTag);
    }

    public String reload() {
        return "/projects?faces-redirect=true";
    }
}
