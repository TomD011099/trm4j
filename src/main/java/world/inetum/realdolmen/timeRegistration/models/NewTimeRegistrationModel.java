package world.inetum.realdolmen.timeRegistration.models;

import world.inetum.realdolmen.timeRegistration.TimeRegistrationRepository;
import world.inetum.realdolmen.validation.PositiveTrmDuration;
import world.inetum.realdolmen.validation.TrmHoursWorkedBelowPurchased;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.inject.Model;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Model
@TrmHoursWorkedBelowPurchased
@PositiveTrmDuration
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
        timeRegistrationRepository.create(this);
        return "trm?faces-red";
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
