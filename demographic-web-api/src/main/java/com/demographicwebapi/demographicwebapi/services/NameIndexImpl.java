package com.demographicwebapi.demographicwebapi.services;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.demographicwebapi.demographicwebapi.models.NameIndexFO;
import com.demographicwebapi.demographicwebapi.repositories.AlgoRepo;
import com.demographicwebapi.demographicwebapi.repositories.NameIndexRepo;
import com.demographicwebapi.helper.Excelhelper;

@Service
public class NameIndexImpl implements NameIndexService {

    @Autowired
    private NameIndexRepo nameIndexDao;

    @Autowired
    private AlgoRepo algoRepo;


    @Override
    public void insertNameIndex(String name, String type, String encode_Type, String jsonString, Long algo) {
        nameIndexDao.save(new NameIndex(name, encode_Type, type, jsonString, algo));
    }


    @Override
    public List<NameIndexFO> fetchResults(String name, String algoName) {
        List<NameIndexFO> rs= new ArrayList<>();
        Long algoID = algoRepo.findByName(algoName).get(0).getId();
        List<NameIndex> nameIndexes = nameIndexDao.findByNameAndAlgoParam(name, algoID);
        return rs;
    }

    @Override
    public void save(MultipartFile file) {
       try {
        List<NameIndex> data = Excelhelper.convertExcelToListOfNameIndex(file.getInputStream());
        this.nameIndexDao.saveAll(data);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @Override
    public List<NameIndex> getAllNameIndex(){
        return this.nameIndexDao.findAll();
    }


    
}
