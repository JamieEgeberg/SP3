/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jamie
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String description;
    private int hoursAssigned;
    private int hoursUsed;

    @ManyToOne
    private Project project;
    
    public Task() {
    }

    public Task(String name, String description, int hoursAssigned) {
        this.name = name;
        this.description = description;
        this.hoursAssigned = hoursAssigned;
        this.hoursUsed = 0;
    }

    public Integer getId() {
        return id;
    }

}
