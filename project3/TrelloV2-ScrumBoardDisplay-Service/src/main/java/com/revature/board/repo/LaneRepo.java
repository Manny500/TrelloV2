package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Lane;

@Repository
public interface LaneRepo extends CrudRepository<Lane,Integer> {
	/**
	 * get a list of all the cards ordered by descending order on L_ID column.
	 * @return - a list of all the Lanes.
	 */
	List<Lane> findAllByOrderByLaneIdDesc();
	
	/**
	 * get a list of all the lanes that have matching B_ID as the bId. 
	 * @param bId - an int representing the board id.
	 * @return - a list of lanes.
	 */
	List<Lane> findBybId(int bId);
	
	/**
	 * save a lane to database
	 */
	@SuppressWarnings("unchecked")
	Lane save(Lane lane);
    
	/**
	 * delete a lane from database
	 */
	void delete(Lane lane);

	
	/**
	 * get a lane where lId matches the laneId param.
	 * @param laneId - an int representing the lane id (a.k.a lId)
	 * @return - a lane
	 */
	Lane findByLaneId(int laneId);
}
