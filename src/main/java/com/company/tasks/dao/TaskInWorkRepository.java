package com.company.tasks.dao;

import com.company.tasks.models.TaskInWork;
import org.springframework.data.repository.CrudRepository;

public interface TaskInWorkRepository extends CrudRepository<TaskInWork, Integer> {
}
