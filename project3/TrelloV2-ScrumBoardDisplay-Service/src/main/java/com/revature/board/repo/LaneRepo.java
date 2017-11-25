package com.revature.board.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.board.beans.Lane;

@Repository
public interface LaneRepo extends CrudRepository<Lane,Integer> {
	List<Lane> findAll();

}
