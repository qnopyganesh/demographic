package com.demographicwebapi.demographicwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demographicwebapi.demographicwebapi.models.NameIndex;

import java.util.List;

@Repository
public interface NameIndexRepo extends JpaRepository<NameIndex,Long> {
    @Query(
        value = "SELECT * FROM name_index ni where ni.name like (SELECT CONCAT('%',:name,'%')) AND algo = :algo",
        nativeQuery = true
    )
    public List<NameIndex> findByNameAndAlgoParam(
        @Param("name") String name,
        @Param("algo") Long algo
    );
}
