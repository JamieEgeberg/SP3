/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Jamie
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar birthDate;
    private int age;
    private boolean isMarried;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Grade grade;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Person supervisor;
    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.REMOVE)
    private List<Person> supervised;

    public Person() {
    }

    public Person(String firstname, String lastname, Calendar birthDate, boolean isMarried, Grade grade, Person supervisor) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.age = Calendar.getInstance().get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        this.isMarried = isMarried;
        this.grade = grade;
        this.supervisor = supervisor;
    }

    public void addToSupervised(Person p) {
        p.setSupervisor(this);
        supervised.add(p);
    }

    public void removeFromSupervised(Person p) {
        supervised.remove(p);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isIsMarried() {
        return isMarried;
    }

    public void setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Person getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Person supervisor) {
        this.supervisor = supervisor;
    }

    public List<Person> getSupervised() {
        return supervised;
    }

    public void setSupervised(List<Person> supervised) {
        this.supervised = supervised;
    }

    public Integer getId() {
        return id;
    }

}
