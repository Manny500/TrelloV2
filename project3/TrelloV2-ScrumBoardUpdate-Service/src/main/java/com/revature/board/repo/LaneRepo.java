package com.revature.board.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Lane;

@Repository
public interface LaneRepo extends CrudRepository<Lane,Integer>{

	//add lane
	@SuppressWarnings("unchecked")
	Lane save(Lane lane);
}
