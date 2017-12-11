package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Board;

@Repository(value="BoardImpl")
public interface BoardRepo extends CrudRepository<Board, Integer> {
	
	/**
	 * get a list of all the boards ordered by descending order on B_ID column.
	 * @return - a list of all the boards.
	 */
	List<Board> findAllByOrderByBIdDesc();
	
	/**
	 * save a board to database.
	 */
	@SuppressWarnings("unchecked")
	Board save(Board board);
	
	/**
	 * delete a board from database.
	 */
	void delete(Board board);
	
	/**
	 * find a board by its B_ID column
	 * @param bId
	 * @return -a board that has matching bId.
	 */
	Board findBybId(int bId);
}
