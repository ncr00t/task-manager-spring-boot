package com.company.tasks.services;

import com.company.tasks.dao.CompletedTaskRepository;
import com.company.tasks.dao.TaskRepository;
import com.company.tasks.models.CompletedTask;
import com.company.tasks.models.Task;
import com.company.tasks.models.TaskInWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.PAForUserEnc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private CompletedTaskRepository completedTaskRepository;

    public TaskService(TaskRepository taskRepository, CompletedTaskRepository completedTaskRepository) {
        this.taskRepository = taskRepository;
        this.completedTaskRepository = completedTaskRepository;
    }

    public void addInCompletedTasks(int id){
        Task findedTask = getTaskById(id);
        findedTask.setFinished(true);
        completedTaskRepository.save(new CompletedTask(findedTask));
    }

    public List<Task> findAllCompletedTasks(){
        List<Task> completedTasks = new ArrayList<>();
        for(CompletedTask task : completedTaskRepository.findAll() ){
            completedTasks.add(task.getTask());
        }
        return completedTasks;
    }

    public List<Task> findAllTasksInWork(){
        List<Task> tasksInWork = new ArrayList<>();
        for(Task task : findAllTasks()){
            if( !task.isFinished() ){
                tasksInWork.add(task);
            }
        }
        return tasksInWork;
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

    public void deleteCompletedTaskById(int id){
        for(CompletedTask completedTask : completedTaskRepository.findAll()){
            if(completedTask.getTask().getId() == id){
                completedTaskRepository.deleteById(completedTask.getId());
            }
        }
    }

    public Task getTaskById(int id){
       return taskRepository.findById(id).get();
    }

    public List<Task> getTaskByName(String name){
        return taskRepository.findByNameContaining(name);
    }

    public List<Task> getTaskByDateCreated(Date dateCreated){
        return taskRepository.findByDateCreated(dateCreated);
    }
}
