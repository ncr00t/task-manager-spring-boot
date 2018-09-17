package com.company.tasks.services;

import com.company.tasks.dao.TaskRepository;
import com.company.tasks.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        List<Task> tasks = new ArrayList<>();
        for(Task task : taskRepository.findAll()){
            tasks.add(task);
        }
        return tasks;
    }

    public void addNewTask(Task task){
        taskRepository.save(task);
    }

    public void deleteTaskById(int id){
        taskRepository.deleteById(id);
    }

    public Task getTaskById(int id){
       return taskRepository.findById(id).get();
    }

    public List<Task> getTaskByName(String name){
        return taskRepository.findByName(name);
    }
}
