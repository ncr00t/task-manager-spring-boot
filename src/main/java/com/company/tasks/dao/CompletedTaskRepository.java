package com.company.tasks.dao;

import com.company.tasks.models.CompletedTask;
import org.springframework.data.repository.CrudRepository;

public interface CompletedTaskRepository extends CrudRepository<CompletedTask,Integer> {

}
