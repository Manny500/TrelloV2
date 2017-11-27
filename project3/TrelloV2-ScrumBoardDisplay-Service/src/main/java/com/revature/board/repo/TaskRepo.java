package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.board.beans.Task;

public interface TaskRepo extends CrudRepository<Task, Integer>{
	List<Task> findAll();
}
