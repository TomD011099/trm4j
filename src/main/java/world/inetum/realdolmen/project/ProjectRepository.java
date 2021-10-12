package world.inetum.realdolmen.project;

import world.inetum.realdolmen.project.models.NewProjectModel;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProjectRepository {

    @PersistenceContext
    EntityManager entityManager;

    private final Logger logger = Logger.getLogger(ProjectRepository.class.getName());

    @RolesAllowed("MANAGER")
    public void create(NewProjectModel newProjectModel) {
        Project project = new Project();

        project.setName(newProjectModel.getName());
        project.setPurchasedHours(Duration.ofHours(newProjectModel.getPurchasedHours()));

        logger.info("create - " + project);

        entityManager.persist(project);
    }

    @RolesAllowed("MANAGER")
    public void deleteById(Long id) {
        Project p = entityManager.find(Project.class, id);
        entityManager.remove(p);
    }

    public List<Project> getAll() {
        return entityManager
                .createQuery("select p from Project p", Project.class)
                .getResultList();
    }

    public Project getById(Long id) {
        return entityManager.find(Project.class, id);
    }

    public Duration getRemainingTimeById(Long id) {
        return getById(id).getTimeLeft();
    }
}
