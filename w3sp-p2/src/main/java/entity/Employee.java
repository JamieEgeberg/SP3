/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Jamie
 */
@Entity
public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int socSecNr;
    private float wage;
    private String taxClass;

    public Employee() {
    }

    public Employee(int socSecNr, float wage, String taxClass, String firstname, String lastname, Calendar birthDate, boolean isMarried, Grade grade, Person supervisor) {
        super(firstname, lastname, birthDate, isMarried, grade, supervisor);
        this.socSecNr = socSecNr;
        this.wage = wage;
        this.taxClass = taxClass;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public int getSocSecNr() {
        return socSecNr;
    }

    public void setSocSecNr(int socSecNr) {
        this.socSecNr = socSecNr;
    }

    public float getWage() {
        return wage;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

}
