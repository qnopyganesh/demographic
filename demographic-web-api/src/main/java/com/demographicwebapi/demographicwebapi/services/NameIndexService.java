package com.demographicwebapi.demographicwebapi.services;

import com.demographicwebapi.demographicwebapi.models.NameIndex;
import com.demographicwebapi.demographicwebapi.models.NameIndexFO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface NameIndexService {
    public void insertNameIndex(String name, String type, String encode_Type, String jsonString, Long algo);

    public List<NameIndexFO> fetchResults(String name, String algoName);

    public void save(MultipartFile file);

    public List<NameIndex> getAllNameIndex();
}
