package com.demographicwebapi.demographicwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demographicwebapi.demographicwebapi.models.Algo;


@Repository
public interface AlgoRepo extends JpaRepository<Algo,Long> {
    
}
