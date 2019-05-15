package com.company.tasks.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This class is a model for the task
 */
@Entity(name = "tasks")
public class Task implements Serializable {
    /**
     * This is the field that stores the task id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**
     * This is the field that stores the name of the task
     */
    private String name;
    /**
     * This is the field that stores a short description of the task
     */
    private String description;
    /**
     * This is the field that stores the date the task was created
     */
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    /**
     * This is the field in which the task finished flag is stored
     */
    private boolean finished;

    /**
     * This is the default constructor
     */
    public Task() {

    }

    /**
     * This is a task constructor that contains the name, description,
     * the date the task was created and the task finished flag
     * @param name task name
     * @param description short description of the task
     * @param dateCreated task creation date
     * @param finished task finished flag
     */
    public Task(String name, String description, Date dateCreated, boolean finished) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.finished = finished;
    }

    /**
     * This is a task constructor that contains the name, description, the date the task was created
     * finished flag is false by default for a new task
     * @param name task name
     * @param description short description of the task
     * @param dateCreated task creation date
     */
    public Task(String name, String description, Date dateCreated) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.finished = false;
    }

    /**
     * This method will return task id
     * @return task id
     */
    public int getId() {
        return id;
    }

    /**
     * This method set the value of the id for the task
     * @param id task id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This method will return task name
     * @return task name
     */
    public String getName() {
        return name;
    }

    /**
     * This method set the value of the name for the task
     * @param name task name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method will return short description of the task
     * @return short description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method set the value of the short description for the task
     * @param description task description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method will return task creation date
     * @return task creation date
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * This method set the value for the task creation date
     * @param dateCreated task creation date to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * This method will return task finished flag
     * @return task finished flag
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * This method set the value for task finished flag
     * @param finished task finished flag
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * This method will return string representation of the class
     * @return string representation of the class
     */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateCreated=" + dateCreated +
                ", finished=" + finished +
                '}';
    }
}
