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
import javax.persistence.Temporal;

/**
 *
 * @author Jamie
 */
@Entity
public class Student extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int matNr;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar matDate;

    public Student() {
    }

    public Student(int matNr, Calendar matDate, String firstname, String lastname, Calendar birthDate, boolean isMarried, Grade grade, Person supervisor) {
        super(firstname, lastname, birthDate, isMarried, grade, supervisor);
        this.matNr = matNr;
        this.matDate = matDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public Calendar getMatDate() {
        return matDate;
    }

    public void setMatDate(Calendar matDate) {
        this.matDate = matDate;
    }

}
