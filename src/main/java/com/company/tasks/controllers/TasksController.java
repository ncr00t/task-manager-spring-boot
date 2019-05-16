package com.company.tasks.controllers;

import com.company.tasks.models.Task;
import com.company.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * This controller allows you to process requests for working with tasks
 */
@Controller
public class TasksController {
    /**
     * This service class instance for work with tasks
     * @see TaskService
     */
    private TaskService taskService;

    /**
     * This constructor for controller
     * @param taskService instance of task service
     */
    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Here the main page is processed
     * @param request some request to go to the main page
     * @return page title
     */
    @GetMapping("/")
    public String home(HttpServletRequest request){
        request.setAttribute("mode", "MODE_HOME");
        return "index";
    }

    /**
     * Here is the page for the output of all tasks
     * @param request some request to go to the all tasks page
     * @return page title
     */
    @GetMapping("/tasks")
    public String tasks(HttpServletRequest request){
        request.setAttribute("tasks", taskService.findAllTasks());
        request.setAttribute("mode","MODE_SHOW_ALL_TASKS");
        return "index";
    }

    /**
     * Here is the page for the output of completed tasks
     * @param request some request to go to the completed tasks page
     * @return page title
     */
    @GetMapping("/completedTasks")
    public String completedasks(HttpServletRequest request){
        request.setAttribute("tasks", taskService.findAllCompletedTasks());
        request.setAttribute("mode","MODE_SHOW_COMPLETED_TASKS");
        return "index";
    }

    /**
     * Here is the page for the output of all tasks in work
     * @param request some request to go to the tasks in work page
     * @return page title
     */
    @GetMapping("/tasksInWork")
    public String tasksInWork(HttpServletRequest request){
        request.setAttribute("tasks", taskService.findAllTasksInWork());
        request.setAttribute("mode", "MODE_SHOW_TASKS_IN_WORK");
        return "index";
    }

    /**
     * Here the task is marked as completed.
     * @param id completed task id
     * @param request some request to go to the completed tasks page
     * @return page title
     */
    @GetMapping("/makeTaskCompleted")
    public String addInCompletedTasks(@RequestParam int id, HttpServletRequest request){
        taskService.makeCompleted(id);
        request.setAttribute("tasks", taskService.findAllCompletedTasks());
        request.setAttribute("mode","MODE_SHOW_ALL_TASKS");
        return "index";
    }

    /**
     * Here is the transition to the page to create a new task
     * @param request some request to go to the new task creation page
     * @return page title
     */
    @GetMapping("/createTask")
    public String createTask(HttpServletRequest request){
        request.setAttribute("mode", "MODE_CREATE_TASK");
        return "index";
    }

    /**
     * Here is the creation of a new task
     * @param task new task class instance
     * @param bindingResult object for validation errors
     * @param request some request to go to the all tasks page
     * @return page title
     */
    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        task.setDateCreated(new Date());
        taskService.addNewTask(task);
        request.setAttribute("tasks",taskService.findAllTasks());
        request.setAttribute("mode", "MODE_SHOW_ALL_TASKS");
        return "index";
    }

    /**
     * Here is the search for the name of the task
     * @param task some task
     * @param bindingResult object for validation errors
     * @param request some request to search by name and transition on all tasks page
     * @return page title
     */
    @PostMapping("/searchTask")
    public String searchTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        request.setAttribute("tasks",  taskService.getTaskByName(task.getName()));
        request.setAttribute("mode", "MODE_SHOW_ALL_TASKS");
        return "index";
    }

    /**
     * Here is the search for the date of the task
     * @param task some task
     * @param bindingResult object for validation errors
     * @param request some request to search by date and transition on all tasks page
     * @return page title
     */
    @PostMapping("/searchTasksByDate")
    public String searchTaskByDate(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        request.setAttribute("tasks", taskService.getTaskByDateCreated(task.getDateCreated()));
        request.setAttribute("mode", "MODE_SHOW_ALL_TASKS");
        return "index";
    }

    /**
     * Here the task is updated
     * @param id task id for update
     * @param request some request for task by id and on update
     * @return page title
     */
    @GetMapping("/updateTask")
    public String updateTask(@RequestParam int id, HttpServletRequest request){
        request.setAttribute("task", taskService.getTaskById(id));
        request.setAttribute("mode", "MODE_UPDATE_TASK");
        return "index";
    }

    /**
     * Here the task is deleted
     * @param id task id for delete
     * @param request some request to go to the all tasks page
     * @return page title
     */
    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam int id, HttpServletRequest request){
        taskService.deleteTaskById(id);
        request.setAttribute("tasks", taskService.findAllTasks());
        request.setAttribute("mode","MODE_SHOW_ALL_TASKS");
        return "index";
    }

    /**
     * Here the completed task is deleted
     * @param id completed task id
     * @param request some request to go the all completed tasks
     * @return page title
     */
    @GetMapping("/deleteCompletedTask")
    public String deleteCompletedTask(@RequestParam int id, HttpServletRequest request){
        taskService.deleteTaskById(id);
        request.setAttribute("tasks", taskService.findAllCompletedTasks());
        request.setAttribute("mode","MODE_SHOW_COMPLETED_TASKS");
        return "index";
    }

    /**
     * Here shows the statistics of completed tasks in relation to all tasks
     * @param request some request to get all the tasks and completed
     * @return page title
     */
    @GetMapping("/statisticsComplete")
    public String statisticsComplete(HttpServletRequest request){
        int countAllTasks = taskService.findAllTasks().size();
        int countCompletedTasks = taskService.findAllCompletedTasks().size();

        request.setAttribute("countAllTasks", countAllTasks);
        request.setAttribute("countCompletedTasks", countCompletedTasks);
        request.setAttribute("mode", "MODE_SHOW_STATISTICS_COMPLETE");
        return "index";
    }

    /**
     * Here shows the statistics of the tasks performed in relation to all tasks
     * and tasks in the work
     * @param request some request to get all the tasks, completed tasks and tasks in work
     * @return page title
     */
    @GetMapping("/statisticsByStagesComplete")
    public String statisticsByStagesComplete(HttpServletRequest request){
        int countAllTasks = taskService.findAllTasks().size();
        int countCompletedTasks = taskService.findAllCompletedTasks().size();
        int countTasksInWork = taskService.findAllTasksInWork().size();

        request.setAttribute("countAllTasks", countAllTasks);
        request.setAttribute("countCompletedTasks", countCompletedTasks);
        request.setAttribute("countTasksInWork", countTasksInWork);
        request.setAttribute("mode", "MODE_SHOW_STATISTICS_BY_STAGES_COMPLETE");
        return "index";
    }
}
