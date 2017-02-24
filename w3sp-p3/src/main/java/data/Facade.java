/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Student;
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

    public Student addStudent(String firstname, String lastname) {
        EntityManager em = new Facade().getManager();
        Student newGuy = new Student(firstname, lastname);
        em.getTransaction().begin();
        em.persist(newGuy);
        em.getTransaction().commit();
        em.close();
        return newGuy;
    }

    public List<Student> findAllStudents() {
        EntityManager em = new Facade().getManager();
        return em.createNamedQuery("Student.findAll")
                .getResultList();
    }

    public List<Student> findByFirstname(String firstname) {
        EntityManager em = new Facade().getManager();
        return em.createNamedQuery("Student.findByFirstname")
                .setParameter("firstname", firstname)
                .getResultList();
    }

    public List<Student> findByLastname(String lastname) {
        EntityManager em = new Facade().getManager();
        return em.createNamedQuery("Student.findByLastname")
                .setParameter("lastname", lastname)
                .getResultList();
    }

    public int getStudypointsTotal() {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT SUM(s.score) FROM Studypoint s ");
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public int getStudypointsByStudentID(int ID) {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT SUM(s.score) FROM Studypoint s WHERE s.student.id = :id ");
        q.setParameter("id", ID);
        return Integer.parseInt(q.getSingleResult().toString());
    }

    public Student getStudentMaxStudypoints() {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT s FROM Student s ");
        List<Student> list = q.getResultList();
        Student s = list.get(0);
        for (Student student : list) {
            if (getStudypointsByStudentID(student.getId()) >= getStudypointsByStudentID(s.getId())) {
                s = student;
            }
        }
        return s;
    }

    public Student getStudentMinStudypoints() {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT s FROM Student s ");
        List<Student> list = q.getResultList();
        Student s = list.get(0);
        for (Student student : list) {
            if (getStudypointsByStudentID(student.getId()) <= getStudypointsByStudentID(s.getId())) {
                s = student;
            }
        }
        return s;
    }

    private EntityManager getManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {

        Facade f = new Facade();
        System.out.println(f.findAllStudents());
        System.out.println(f.findByFirstname("jan"));
        System.out.println(f.findByLastname("Olsen"));
        System.out.println(f.getStudypointsTotal());
        System.out.println("by ID: "+f.getStudypointsByStudentID(2));
        System.out.println(f.getStudentMaxStudypoints().getFirstname() + f.getStudentMaxStudypoints().getLastname());
        System.out.println(f.getStudentMinStudypoints().getFirstname() + f.getStudentMinStudypoints().getLastname());

    }
}
