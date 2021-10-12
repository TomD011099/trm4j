package world.inetum.realdolmen.locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Named
@ApplicationScoped
public class LanguageOptionsBean {

    private List<Locale> locales = Arrays.asList(
            Locale.ENGLISH,
            new Locale("nl")
    );

    public List<Locale> getLocales() {
        return locales;
    }
}
