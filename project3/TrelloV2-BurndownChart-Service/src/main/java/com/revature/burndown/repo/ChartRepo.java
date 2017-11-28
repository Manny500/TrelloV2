package com.revature.burndown.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.revature.burndown.bean.Chart;

@Repository(value="chartRepoImpl")
public interface ChartRepo extends CrudRepository<Chart,Integer>{
	
	List<Chart> findAll();
	
	List<Chart> findByChartBoard(int chartBoard);

	@SuppressWarnings("unchecked")
	Chart save(Chart board);
}
