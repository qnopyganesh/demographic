package com.demographicwebapi.demographicwebapi.services;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demographicwebapi.demographicwebapi.models.Algo;
import com.demographicwebapi.demographicwebapi.models.NameIndex;
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
    public void insertNameIndex(String name, char type, String encode, String name_json,String encode_json, Long algo) {
        nameIndexDao.save(new NameIndex(name, type, encode, name_json, encode_json, algo));
    }


    @Override
    public List<NameIndex> fetchResults(String name, String algoName, boolean isSurname) {
        List<Algo> algos = algoRepo.findByName(algoName);
        Long algoID;
        if(algos.size() > 0){
            algoID = algos.get(0).getId();
        }
        else{
            algoID = null;
            return new ArrayList<NameIndex>();
        }
        List<NameIndex> nameIndexes = new ArrayList<>();
        if(isSurname){
            nameIndexes = nameIndexDao.findBySNameAndAlgoParam(name, algoID);
        }
        else{
            nameIndexes = nameIndexDao.findByfNameAndAlgoParam(name, algoID);

        }
        return nameIndexes;
    }

    @Override
    public void save(MultipartFile file) {
       try {
        String fileName = file.getOriginalFilename();
        String algoName = fileName.split("_")[0];
        boolean isSurname = false;
        if (fileName.split("_")[1].equals("surname.csv.result.csv")){
            isSurname = true;
        }
        Long algoId ;
        if (algoRepo.findByName(algoName).size() == 0){
            Algo algo = new Algo(algoName);
            algoRepo.save(algo);
        }
        algoId = algoRepo.findByName(algoName).get(0).getId();
        List<NameIndex> data = Excelhelper.convertExcelToListOfNameIndex(file.getInputStream(),algoId,isSurname,fileName);
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
