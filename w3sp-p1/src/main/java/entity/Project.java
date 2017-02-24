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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Jamie
 */
@Entity
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastModified;

    @ManyToMany(mappedBy = "projects")
    private List<ProjectUser> users;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    public Project() {
    }

    public Integer getId() {
        return id;
    }

    public Project(String name, String desc) {
        this.name = name;
        this.description = desc;
        created.setTime(System.currentTimeMillis());
        lastModified.setTime(System.currentTimeMillis());
    }

    public void addUser(ProjectUser user) {
        users.add(user);
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
}
