package com.demographicwebapi.demographicwebapi.services;

import com.demographicwebapi.demographicwebapi.models.Algo;
import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.demographicwebapi.demographicwebapi.models.User;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface NameIndexService {
    public void insertNameIndex(String name, char type, String encode, String name_json,String encode_json, Long algo);

    public List<NameIndex> fetchResults(String name, String algoName,boolean isSurname);

    public void save(MultipartFile file);

    public List<NameIndex> getAllNameIndex();

    public List<Algo> fetchAlgos();

    public void saveUserDetails(MultipartFile file);

    public User fetchUserDetails(String firstname, String lastname,String emailId, String phonenumber, String dob);

}
