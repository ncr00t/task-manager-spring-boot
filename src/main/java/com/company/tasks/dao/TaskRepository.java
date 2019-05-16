package com.company.tasks.dao;

import com.company.tasks.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * This repository allows to search for tasks in the database
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    /**
     * Here is a selection of tasks by letters in name or name
     * @param taskName some task name
     * @return list of tasks found by letters in name or name
     */
    List<Task> findByNameContaining(String taskName);

    /**
     * Here is a selection of tasks by date of creation
     * @param date task creation date
     * @return list of tasks found by creation date
     */
    List<Task> findByDateCreated(Date date);
}
