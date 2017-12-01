package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Card;
import com.revature.board.beans.Task;

@Repository
public interface CardRepo extends CrudRepository<Card,Integer>{
	List<Card> findAllByOrderByCIdAsc();
	
	List<Card> findByLId(int lId);
	
	@SuppressWarnings("unchecked")
	Card save(Card card);
	
	 void delete(Card card);
}
