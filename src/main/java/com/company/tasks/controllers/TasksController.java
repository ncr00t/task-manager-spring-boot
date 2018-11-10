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

@Controller
public class TasksController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String home(HttpServletRequest request){
        request.setAttribute("mode", "MODE_HOME");
        return "index";
    }

    @GetMapping("/tasks")
    public String tasks(HttpServletRequest request){
        request.setAttribute("tasks", taskService.findAllTasks());
        request.setAttribute("mode","MODE_SHOW_ALL_TASKS");
        return "index";
    }

    @GetMapping("/completedTasks")
    public String completedasks(HttpServletRequest request){
        request.setAttribute("tasks", taskService.findAllCompletedTasks());
        request.setAttribute("mode","MODE_SHOW_COMPLETED_TASKS");
        return "index";
    }

    @GetMapping("/tasksInWork")
    public String tasksInWork(HttpServletRequest request){
        request.setAttribute("tasks", taskService.findAllTasksInWork());
        request.setAttribute("mode", "MODE_SHOW_TASKS_IN_WORK");
        return "index";
    }

    @GetMapping("/makeTaskCompleted")
    public String addInCompletedTasks(@RequestParam int id, HttpServletRequest request){
        taskService.makeCompleted(id);
        request.setAttribute("tasks", taskService.findAllCompletedTasks());
        request.setAttribute("mode","MODE_SHOW_ALL_TASKS");
        return "index";
    }

    @GetMapping("/createTask")
    public String createTask(HttpServletRequest request){
        request.setAttribute("mode", "MODE_CREATE_TASK");
        return "index";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        task.setDateCreated(new Date());
        taskService.addNewTask(task);
        request.setAttribute("tasks",taskService.findAllTasks());
        request.setAttribute("mode", "MODE_SHOW_ALL_TASKS");
        return "index";
    }

    @PostMapping("/searchTask")
    public String searchTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        request.setAttribute("tasks",  taskService.getTaskByName(task.getName()));
        request.setAttribute("mode", "MODE_SHOW_ALL_TASKS");
        return "index";
    }

    @PostMapping("/searchTasksByDate")
    public String searchTaskByDate(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request){
        request.setAttribute("tasks", taskService.getTaskByDateCreated(task.getDateCreated()));
        request.setAttribute("mode", "MODE_SHOW_ALL_TASKS");
        return "index";
    }

    @GetMapping("/updateTask")
    public String updateTask(@RequestParam int id, HttpServletRequest request){
        request.setAttribute("task", taskService.getTaskById(id));
        request.setAttribute("mode", "MODE_UPDATE_TASK");
        return "index";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam int id, HttpServletRequest request){
        taskService.deleteTaskById(id);
        request.setAttribute("tasks", taskService.findAllTasks());
        request.setAttribute("mode","MODE_SHOW_ALL_TASKS");
        return "index";
    }

    @GetMapping("/deleteCompletedTask")
    public String deleteCompletedTask(@RequestParam int id, HttpServletRequest request){
        taskService.deleteCompletedTaskById(id);
        taskService.deleteTaskById(id);
        request.setAttribute("tasks", taskService.findAllCompletedTasks());
        request.setAttribute("mode","MODE_SHOW_COMPLETED_TASKS");
        return "index";
    }
}
