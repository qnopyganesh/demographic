package com.demographicwebapi.demographicwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demographicwebapi.demographicwebapi.models.NameIndex;

@Repository
public interface UserIndexRepo extends JpaRepository<NameIndex,Long> {
    
}
