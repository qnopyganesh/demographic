package com.demographicwebapi.demographicwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demographicwebapi.demographicwebapi.models.Algo;
import java.util.*;
public interface AlgoRepo extends JpaRepository<Algo,Long> {
    public List<Algo> findByName(String name);
}
