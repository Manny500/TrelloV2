package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer>{
	/**
	 * get a list of all the tasks ordered by descending order on T_ID column.
	 * @return - a list of all the Tasks.
	 */
	List<Task> findAllByOrderByTaskIdDesc();
	
	/**
	 * get a list of all the tasks that have matching C_ID as the cardId. 
	 * @param bId - an int representing the card id.
	 * @return - a list of tasks.
	 */
	List<Task> findByCardId(int cardId);
	
	/**
	 * save task to database.
	 */
	@SuppressWarnings("unchecked")
	Task save(Task task);
	
	/**
	 * delete task from database.
	 */
	void delete(Task task);
	
}
