package com.revature.permissions.repo;

import java.util.List;

import javax.persistence.OrderBy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.permissions.beans.Activity;
import com.revature.permissions.beans.TV2User;

@Repository
public interface ActivityRepo extends CrudRepository<Activity,Integer>{

	/**
	 * save activity to the database
	 */
	@SuppressWarnings("unchecked")
	Activity save(Activity activity);
	
//	@OrderBy
//	List<Activity> findAll();
	
	/**
	 * get a list of all the activities from the database and order by ascending order on
	 * the A_ID column
	 * @return
	 */
	List<Activity> findAllByOrderByAIdAsc();
}
