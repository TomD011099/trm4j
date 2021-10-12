package world.inetum.realdolmen.timeRegistration.models;

import world.inetum.realdolmen.timeRegistration.TimeRegistration;
import world.inetum.realdolmen.timeRegistration.TimeRegistrationRepository;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import java.util.List;

@Model
public class TimeRegistrationListModel {
    List<TimeRegistration> timeRegistrations;

    @EJB
    TimeRegistrationRepository timeRegistrationRepository;

    public List<TimeRegistration> getTimeRegistrations() {
        return timeRegistrationRepository.getByCurrentConsultant();
    }

    public void setTimeRegistrations(List<TimeRegistration> timeRegistrations) {
        this.timeRegistrations = timeRegistrations;
    }
}
