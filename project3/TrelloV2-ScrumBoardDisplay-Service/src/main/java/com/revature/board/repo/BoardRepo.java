package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Board;

@Repository(value="BoardImpl")
public interface BoardRepo extends CrudRepository<Board, Integer> {
	List<Board> findAllByOrderByBIdAsc();
	
	@SuppressWarnings("unchecked")
	Board save(Board board);
	
	void delete(Board board);
}
