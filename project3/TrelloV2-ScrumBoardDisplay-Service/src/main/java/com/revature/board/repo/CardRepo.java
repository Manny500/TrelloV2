package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Card;

@Repository
public interface CardRepo extends CrudRepository<Card,Integer>{
	List<Card> findAllByOrderByCIdDesc();
	
	List<Card> findByLId(int lId);
	
	@SuppressWarnings("unchecked")
	Card save(Card card);
	
	 void delete(Card card);
}
