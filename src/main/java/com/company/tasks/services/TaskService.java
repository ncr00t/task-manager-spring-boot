package com.company.tasks.services;

import com.company.tasks.dao.TaskRepository;
import com.company.tasks.models.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This service allows you to work with tasks using the repository
 */
@Service
@Transactional
public class TaskService {
    /**
     * Repository class instance which allows to search for tasks in the database
     * @see TaskRepository
     */
    private final TaskRepository taskRepository;

    /**
     * This constructor for service
     * @param taskRepository instance of task repository
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Here the task is marked as completed
     * @param id completed task id
     */
    public void makeCompleted(int id){
        Task findedTask = getTaskById(id);
        findedTask.setFinished(true);
    }

    /**
     * Here is the search for all completed tasks
     * @return list of completed tasks
     */
    public List<Task> findAllCompletedTasks(){
        List<Task> completedTasks = new ArrayList<>();
        for(Task task : taskRepository.findAll() ){
            if( task.isFinished() ) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    /**
     * Here is the search for all tasks in work
     * @return list of task in work
     */
    public List<Task> findAllTasksInWork(){
        List<Task> tasksInWork = new ArrayList<>();
        for(Task task : findAllTasks()){
            if( !task.isFinished() ){
                tasksInWork.add(task);
            }
        }
        return tasksInWork;
    }

    /**
     * Here is the search for all tasks
     * @return list of all tasks
     */
    public List<Task> findAllTasks(){
        List<Task> tasks = new ArrayList<>();
        for(Task task : taskRepository.findAll()){
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Here is the creation of a new task
     * @param task new task
     */
    public void addNewTask(Task task){
        taskRepository.save(task);
    }

    /**
     * Here the task is deleted by id
     * @param id task id for delete
     */
    public void deleteTaskById(int id){
        taskRepository.deleteById(id);
    }

    /**
     * Here get task by id
     * @param id task id
     * @return task with this id
     */
    public Task getTaskById(int id){
       return taskRepository.findById(id).get();
    }

    /**
     * Here get task by name
     * @param name task name
     * @return task with this name
     */
    public List<Task> getTaskByName(String name){
        return taskRepository.findByNameContaining(name);
    }

    /**
     * Here get task by date created
     * @param dateCreated task creation date
     * @return task with this date created
     */
    public List<Task> getTaskByDateCreated(Date dateCreated){
        return taskRepository.findByDateCreated(dateCreated);
    }
}
