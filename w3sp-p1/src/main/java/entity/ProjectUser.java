/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Jamie
 */
@Entity
public class ProjectUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    @ManyToMany
    private List<Project> projects;

    public ProjectUser() {
    }

    public Integer getId() {
        return id;
    }

    public ProjectUser(String un, String e) {
        this.userName = un;
        this.email = e;
        created.setTime(System.currentTimeMillis());
    }

    public void addProject(Project proj) {
        projects.add(proj);
    }

}
