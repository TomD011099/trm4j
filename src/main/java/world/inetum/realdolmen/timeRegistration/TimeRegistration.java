package world.inetum.realdolmen.timeRegistration;

import world.inetum.realdolmen.project.Project;
import world.inetum.realdolmen.validation.PositiveDuration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TimeRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_fk", nullable = false)
    private Project project;

    @NotNull
    @Column(name = "day", nullable = false)
    private LocalDate day;

    @NotNull
    @PositiveDuration
    @Column(name = "duration", nullable = false)
    private Duration duration;

    @NotNull
    @NotBlank
    @Column(name = "consultant", nullable = false, updatable = false)
    private String consultant;

    public TimeRegistration() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeRegistration that = (TimeRegistration) o;
        return id.equals(that.id) && project.equals(that.project) && day.equals(that.day) && duration.equals(that.duration) && consultant.equals(that.consultant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, day, duration, consultant);
    }

    @Override
    public String toString() {
        return "TimeRegistration{" +
                "id=" + id +
                ", project=" + project +
                ", day=" + day +
                ", duration=" + duration +
                ", consultant='" + consultant + '\'' +
                '}';
    }
}
