package com.demographicwebapi.demographicwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.demographicwebapi.demographicwebapi.models.Algo;
import com.demographicwebapi.demographicwebapi.repositories.AlgoRepo;

public class AlgoImpl implements AlgoService {
	
	@Autowired
	private AlgoRepo AlgoDao;

	@Override
	public void insertAlgo(String name) {
		AlgoDao.save(new Algo(name));
		// TODO Auto-generated method stub

	}

}
