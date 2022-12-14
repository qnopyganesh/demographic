package com.demographicwebapi.demographicwebapi.services;

import com.demographicwebapi.demographicwebapi.models.Algo;

public interface AlgoService {

    public Algo getAlgo(String name);

    public String encodeString(String name, String algoName);
}
