/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Project;
import entity.ProjectUser;
import entity.Task;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jamie
 */
public class Facade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU", null);

    public void createUser(String userName, String email) {
        EntityManager em = new Facade().getManager();
        ProjectUser newGuy = new ProjectUser(userName, email);
        em.getTransaction().begin();
        em.persist(newGuy);
        em.getTransaction().commit();
    }

    public ProjectUser findUser(String userName) {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT p FROM ProjectUser p WHERE p.userName = :userName");
        q.setParameter("userName", userName);
        return (ProjectUser) q.getSingleResult();
    }

    public List<ProjectUser> getAllUsers() {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT c FROM ProjectUser c ");
        return q.getResultList();
    }

    public void createProject(String name, String desc) {
        EntityManager em = new Facade().getManager();
        Project newProj = new Project(name, desc);
        em.getTransaction().begin();
        em.persist(newProj);
        em.getTransaction().commit();
    }

    public void addUserToProject(ProjectUser user, Project proj) {
        proj.addUser(user);
        user.addProject(proj);
    }

    public Project findProject(String name) {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT p FROM Project p WHERE p.name = :name");
        q.setParameter("name", name);
        return (Project) q.getSingleResult();
    }

    public void CreateTaskAndAssignToProject(String name, String description, int hoursAssigned, Project proj) {
        Task t = new Task(name, description, hoursAssigned);
        proj.addTask(t);
    }

    private EntityManager getManager() {
        return emf.createEntityManager();
    }
}
