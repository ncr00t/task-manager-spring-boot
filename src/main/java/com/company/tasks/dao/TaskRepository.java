package com.company.tasks.dao;

import com.company.tasks.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByNameContaining(String taskName);
    List<Task> findByDateCreated(Date date);
}
