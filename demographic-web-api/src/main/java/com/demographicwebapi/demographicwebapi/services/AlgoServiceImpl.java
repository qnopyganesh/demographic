package com.demographicwebapi.demographicwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demographicwebapi.demographicwebapi.models.Algo;
import com.demographicwebapi.demographicwebapi.repositories.AlgoRepo;

@Service
public class AlgoServiceImpl implements AlgoService {
    @Autowired
    private AlgoRepo algoRepo;


    @Override
    public Algo getAlgo(String name) {
        return algoRepo.findByName(name).get(0);
    }
    
    
}
