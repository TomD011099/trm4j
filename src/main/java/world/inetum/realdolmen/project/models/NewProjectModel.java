package world.inetum.realdolmen.project.models;

import world.inetum.realdolmen.project.ProjectRepository;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Model
public class NewProjectModel {

    @NotNull
    @Positive
    private Integer purchasedHours;

    @NotNull
    @NotEmpty
    private String name;

    @EJB
    ProjectRepository projectRepository;

    public String create() {
        projectRepository.create(this);
        return "projects?faces-red";
    }

    public Integer getPurchasedHours() {
        return purchasedHours;
    }

    public void setPurchasedHours(Integer purchasedHours) {
        this.purchasedHours = purchasedHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}