package world.inetum.realdolmen.timeRegistration;

import world.inetum.realdolmen.project.Project;
import world.inetum.realdolmen.security.CurrentUserService;
import world.inetum.realdolmen.timeRegistration.models.NewTimeRegistrationModel;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class TimeRegistrationRepository {

    @PersistenceContext
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(TimeRegistrationRepository.class.getName());

    @EJB
    CurrentUserService currentUserService;

    @RolesAllowed({"MANAGER", "CONSULTANT"})
    public void create(@Valid NewTimeRegistrationModel timeRegistration) {
        TimeRegistration trm = new TimeRegistration();

        LocalTime start = timeRegistration.getStartTime();
        LocalTime end = timeRegistration.getEndTime();

        trm.setDay(timeRegistration.getDay());
        trm.setDuration(Duration.between(start, end));
        trm.setConsultant(currentUserService.getCurrentUserName());

        Project p = entityManager.find(Project.class, timeRegistration.getProjectId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        trm.setProject(p);
        p.addTrm(trm);

        logger.info("Creating new time registration: " + trm);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        entityManager.persist(trm);
    }

    public List<TimeRegistration> getAll() {
        return entityManager
                .createQuery("select t from TimeRegistration t", TimeRegistration.class)
                .getResultList();
    }

    public List<TimeRegistration> getByConsultant(String consultant) {
        return entityManager.createQuery("select t from TimeRegistration t where t.consultant = ?1", TimeRegistration.class)
                .setParameter(1, consultant)
                .getResultList();
    }

    public List<TimeRegistration> getByCurrentConsultant() {
        return getByConsultant(currentUserService.getCurrentUserName());
    }

    public TimeRegistration getById(Long id) {
        return entityManager.find(TimeRegistration.class, id);
    }

    public List<TimeRegistration> getByProjectName(String name) {
        return entityManager
                .createQuery("select t from TimeRegistration t left join Project where t.project.name = ?1", TimeRegistration.class)
                .setParameter(1, name)
                .getResultList();
    }

    public List<TimeRegistration> getByProjectId(Long id) {
        return entityManager
                .createQuery("select t from TimeRegistration t left join Project where t.project.id = ?1", TimeRegistration.class)
                .setParameter(1, id)
                .getResultList();
    }
}
