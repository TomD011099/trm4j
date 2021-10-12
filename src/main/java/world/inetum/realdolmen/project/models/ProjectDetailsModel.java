package world.inetum.realdolmen.project.models;

import world.inetum.realdolmen.project.Project;
import world.inetum.realdolmen.project.ProjectRepository;
import world.inetum.realdolmen.timeRegistration.TimeRegistration;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import java.util.List;

@Model
public class ProjectDetailsModel {

    private Long id;

    private String name;

    private String purchasedHours;

    private List<TimeRegistration> timeRegistrations;

    @EJB
    private ProjectRepository projectRepository;

    public void load() {
        Project p = projectRepository.getById(this.id);

        this.name = p.getName();
        this.purchasedHours = p.getFormattedDuration();
        this.timeRegistrations = p.getTimeRegistrations();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurchasedHours() {
        return purchasedHours;
    }

    public void setPurchasedHours(String purchasedHours) {
        this.purchasedHours = purchasedHours;
    }

    public List<TimeRegistration> getTimeRegistrations() {
        return timeRegistrations;
    }

    public void setTimeRegistrations(List<TimeRegistration> timeRegistrations) {
        this.timeRegistrations = timeRegistrations;
    }
}
