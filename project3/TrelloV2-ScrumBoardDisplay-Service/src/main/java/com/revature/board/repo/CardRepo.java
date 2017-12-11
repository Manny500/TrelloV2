package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Card;

@Repository
public interface CardRepo extends CrudRepository<Card,Integer>{
	
	/**
	 * get a list of all the cards ordered by descending order on C_ID column.
	 * @return - a list of all the cards.
	 */
	List<Card> findAllByOrderByCIdDesc();
	
	/**
	 * get a list of all the cards that have matching L_ID as the lId. 
	 * @param lId - an int representing the lane id.
	 * @return - a list of cards.
	 */
	List<Card> findByLId(int lId);
	
	/**
	 * save a card to database.
	 */
	@SuppressWarnings("unchecked")
	Card save(Card card);
	
	/**
	 * delete a card from database.
	 */
	 void delete(Card card);
}
