package com.demographicwebapi.demographicwebapi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demographicwebapi.demographicwebapi.models.Algo;
import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.demographicwebapi.demographicwebapi.repositories.AlgoRepo;
import com.demographicwebapi.demographicwebapi.repositories.NameIndexRepo;
import com.google.gson.Gson;

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
        convertExcelToListOfNameIndex(file.getInputStream(),algoId,isSurname,fileName);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    @Async
    public List<NameIndex> convertExcelToListOfNameIndex(InputStream is, Long algoId, boolean isSurname,
            String fileName) {

        List<NameIndex> list = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));) {
            fileReader.readLine();
            String line1 = "";
            String line2 = "";
            while ((line1 = fileReader.readLine()) != null) {
                line2 = fileReader.readLine();
                String tokens1[] = line1.split(",");
                String tokens2[] = line2.split(",");
                int totalTokens = tokens1.length;
                NameIndex nameIndex = new NameIndex();
                nameIndex.setAlgo(algoId);
                nameIndex.setName(tokens1[2]);
                nameIndex.setEncode(tokens2[2]);
                ArrayList<String> name_jsonArray = new ArrayList<>();
                ArrayList<String> encode_jsonArray = new ArrayList<>();
                for(int i = 3 ; i < totalTokens ; i++){
                    name_jsonArray.add(tokens1[i]);
                    encode_jsonArray.add(tokens2[i]);
                }
                Gson gson = new Gson();
                nameIndex.setName_json(gson.toJson(name_jsonArray));
                nameIndex.setEncode_json(gson.toJson(encode_jsonArray));
                nameIndex.setType(isSurname ? 's' : 'f');
                nameIndexDao.save(nameIndex);
            }
        }
         catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    @Override
    public List<NameIndex> getAllNameIndex(){
        return this.nameIndexDao.findAll();
    }


    @Override
    public List<Algo> fetchAlgos() {
        return algoRepo.findAll();
    }

    
}
