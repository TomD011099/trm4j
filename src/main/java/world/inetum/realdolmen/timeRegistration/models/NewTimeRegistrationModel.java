package world.inetum.realdolmen.timeRegistration.models;

import world.inetum.realdolmen.timeRegistration.TimeRegistrationRepository;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

//TODO add validation for positive time
@Model
public class NewTimeRegistrationModel {

    @NotNull
    private Long projectId;

    @NotNull
    private LocalDate day;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    @EJB
    TimeRegistrationRepository timeRegistrationRepository;

    public String create() {
        System.out.println("Creating new time registration: " + this);
        timeRegistrationRepository.create(this);
        return "trm?faces-red";
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


}
