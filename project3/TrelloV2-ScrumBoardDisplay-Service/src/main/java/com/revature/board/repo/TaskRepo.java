package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer>{
	List<Task> findAllByOrderByTaskIdDesc();
	
	List<Task> findByCardId(int cardId);
	
	
	@SuppressWarnings("unchecked")
	Task save(Task task);
	
	void delete(Task task);
	
}
