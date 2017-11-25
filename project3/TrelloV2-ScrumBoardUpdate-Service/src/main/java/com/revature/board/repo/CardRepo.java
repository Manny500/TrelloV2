package com.revature.board.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Card;

@Repository
public interface CardRepo extends CrudRepository<Card,Integer>{
	@SuppressWarnings("unchecked")
	Card save(Card card);
}
