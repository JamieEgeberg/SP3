/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Employee;
import entity.Grade;
import entity.Person;
import entity.Student;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    public void addPerson(String firstname, String lastname, Calendar birthDate,
            boolean isMarried, Grade grade, Person supervisor) {

        EntityManager em = new Facade().getManager();
        Person newGuy = new Person(firstname, lastname, birthDate, isMarried, grade, supervisor);
        em.getTransaction().begin();
        em.persist(grade);
        em.persist(newGuy);
        em.getTransaction().commit();
        em.close();
    }

    public Student addStudent(int matNr, Calendar matDate, String firstname, String lastname,
            Calendar birthDate, boolean isMarried, Grade grade, Person supervisor) {

        EntityManager em = new Facade().getManager();
        Student newGuy = new Student(matNr, matDate, firstname, lastname, birthDate, isMarried, grade, supervisor);
        em.getTransaction().begin();
        em.persist(grade);
        em.persist(newGuy);
        em.getTransaction().commit();
        em.close();
        return newGuy;
    }

    public Employee addEmployee(int socSecNr, float wage, String taxClass, String firstname,
            String lastname, Calendar birthDate, boolean isMarried, Grade grade, Person supervisor) {

        EntityManager em = new Facade().getManager();
        Employee newGuy = new Employee(socSecNr, wage, taxClass, firstname, lastname, birthDate, isMarried, grade, supervisor);
        em.getTransaction().begin();
        em.persist(grade);
        em.persist(newGuy);
        em.getTransaction().commit();
        em.close();
        return newGuy;
    }

    public void addPerson(Person person) {
        EntityManager em = new Facade().getManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        em.close();
    }

    public void addStudent(Student student) {

        EntityManager em = new Facade().getManager();
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
    }

    public void addEmployee(Employee employee) {

        EntityManager em = new Facade().getManager();
        em.getTransaction().begin();
        em.merge(employee);
        em.getTransaction().commit();
        em.close();
    }

    public Person findPerson(String firstname, String lastname) {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.firstname = :firstname AND p.lastname = :lastname");
        q.setParameter("firstname", firstname);
        q.setParameter("lastname", lastname);
        return (Person) q.getSingleResult();
    }

    public Student findStudent(String firstname, String lastname) {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT s FROM Student s WHERE s.firstname = :firstname AND s.lastname = :lastname");
        q.setParameter("firstname", firstname);
        q.setParameter("lastname", lastname);
        return (Student) q.getSingleResult();
    }

    public Employee findEmployee(String firstname, String lastname) {
        EntityManager em = new Facade().getManager();
        Query q = em.createQuery("SELECT e FROM Employee e  WHERE e.firstname = :firstname AND e.lastname = :lastname");
        q.setParameter("firstname", firstname);
        q.setParameter("lastname", lastname);
        System.out.println(q.getResultList());
        return (Employee) q.getSingleResult();
    }

    public void removePerson(Person p) {
        EntityManager em = new Facade().getManager();
        em.getTransaction().begin();
        p.getSupervisor().removeFromSupervised(p);
        p.setSupervisor(null);
        Person toBeRemoved = em.merge(p);
        em.remove(toBeRemoved);
        em.getTransaction().commit();
        em.close();
    }

    public void removeStudent(Student s) {
        EntityManager em = new Facade().getManager();
        em.getTransaction().begin();
        s.getSupervisor().removeFromSupervised(s);
        s.setSupervisor(null);
        Student toBeRemoved = em.merge(s);
        em.remove(toBeRemoved);
        em.getTransaction().commit();
        em.close();
    }

    public void removeEmployee(Employee e) {
        EntityManager em = new Facade().getManager();
        em.getTransaction().begin();
        e.getSupervisor().removeFromSupervised(e);
        e.setSupervisor(null);
        Employee toBeRemoved = em.merge(e);
        em.remove(toBeRemoved);
        em.getTransaction().commit();
        em.close();
    }

    private EntityManager getManager() {
        return emf.createEntityManager();
    }

    public static void main(String[] args) {
        Facade f = new Facade();
        f.addPerson("Jimbo", "Jones", new GregorianCalendar(1992, 7, 19), false, new Grade("års", 10), null);
        Employee e = f.addEmployee(8888, 75000.5F, "TheBest", "Arne", "Arnested", new GregorianCalendar(1985, 2, 15), true, new Grade("års", 10), null);
        f.addStudent(5, new GregorianCalendar(2016, 7, 25), "Berit", "Brandsen", new GregorianCalendar(1995, 5, 18), true, new Grade("års", 10), e);
        Student s = f.addStudent(6, new GregorianCalendar(2016, 8, 25), "Carl", "Conrad", new GregorianCalendar(1990, 5, 18), true, new Grade("års", 02), e);
        System.out.println(f.findEmployee("Arne", "Arnested"));
        System.out.println(s);
        f.removePerson(s);
    }
}
