package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Card;

@Repository
public interface CardRepo extends CrudRepository<Card,Integer>{
	List<Card> findAll();
	
	@SuppressWarnings("unchecked")
	Card save(Card card);
}
