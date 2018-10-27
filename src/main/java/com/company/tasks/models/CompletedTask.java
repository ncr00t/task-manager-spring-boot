package com.company.tasks.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "completed_tasks")
public class CompletedTask implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Task task;

    public CompletedTask() {

    }

    public CompletedTask(Task task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
