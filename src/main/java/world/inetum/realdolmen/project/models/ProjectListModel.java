package world.inetum.realdolmen.project.models;

import world.inetum.realdolmen.project.Project;
import world.inetum.realdolmen.project.ProjectRepository;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import java.util.List;

@Model
public class ProjectListModel {
    List<Project> projects;

    @EJB
    ProjectRepository projectRepository;

    public List<Project> getProjects() {
        return projectRepository.getAll();
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
