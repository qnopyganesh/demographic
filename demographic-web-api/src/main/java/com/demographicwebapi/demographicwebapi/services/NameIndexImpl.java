package com.demographicwebapi.demographicwebapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.demographicwebapi.demographicwebapi.repositories.UserIndexRepo;

@Service
public class NameIndexImpl implements NameIndexService {

    @Autowired
    private UserIndexRepo nameIndexDao;


    @Override
    public void insertNameIndex(String name, String type, String encode_Type, String jsonString, Long algo) {
        nameIndexDao.save(new NameIndex(name, encode_Type, type, jsonString, algo));
        
    }
    
}
