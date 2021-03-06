package world.inetum.realdolmen.project;

import world.inetum.realdolmen.timeRegistration.TimeRegistration;
import world.inetum.realdolmen.validation.HoursWorkedBelowPurchased;
import world.inetum.realdolmen.validation.PositiveDuration;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@HoursWorkedBelowPurchased
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @PositiveDuration
    @Column(name = "purchased_hours", nullable = false)
    private Duration purchasedHours;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TimeRegistration> timeRegistrations;

    @Version
    private Integer version;

    public Project() {
        this.timeRegistrations = new ArrayList<>();
    }

    public Project(Long id, String name, Duration purchasedHours) {
        this.id = id;
        this.name = name;
        this.purchasedHours = purchasedHours;
        this.timeRegistrations = new ArrayList<>();
    }

    public Duration getWorkedTime() {
        Duration total = Duration.ZERO;

        for (Duration d : timeRegistrations.stream().map(TimeRegistration::getDuration).collect(Collectors.toList())) {
            total = total.plus(d);
        }

        return total;
    }

    public Duration getTimeLeft() {
        return purchasedHours.minus(getWorkedTime());
    }

    public void addTrm(TimeRegistration trm) {
        this.timeRegistrations.add(trm);
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

    public Duration getPurchasedHours() {
        return purchasedHours;
    }

    public void setPurchasedHours(Duration purchasedHours) {
        this.purchasedHours = purchasedHours;
    }

    public List<TimeRegistration> getTimeRegistrations() {
        return timeRegistrations;
    }

    public void setTimeRegistrations(List<TimeRegistration> timeRegistrations) {
        this.timeRegistrations = timeRegistrations;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchasedHours=" + purchasedHours +
                '}';
    }
}
