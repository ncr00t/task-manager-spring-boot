package com.company.tasks.dao;

import com.company.tasks.models.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByName(String name);
}
