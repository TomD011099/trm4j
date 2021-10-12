package world.inetum.realdolmen;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.Duration;

@FacesConverter("DurationConverter")
public class DurationConverter implements Converter<Duration> {
    @Override
    public Duration getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        Duration duration = Duration.ofHours(Long.parseLong(s.split(":")[0]));
        duration = duration.plus(Duration.ofMinutes(Long.parseLong(s.split(":")[1])));

        return duration;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Duration o) {
        return String.format("%d:%02d",
                o.toHours(),
                o.toMinutes() % 60);
    }
}
