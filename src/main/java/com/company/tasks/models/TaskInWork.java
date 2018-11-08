package com.company.tasks.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tasks_in_work")
public class TaskInWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Task task;

    public TaskInWork() {

    }

    public TaskInWork(Task task) {
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
