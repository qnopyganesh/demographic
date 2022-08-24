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
        value = "SELECT * FROM name_index ni where ni.name = :name AND algo = :algo AND type = 'f'",
        nativeQuery = true
    )
    public List<NameIndex> findByfNameAndAlgoParam(
        @Param("name") String name,
        @Param("algo") Long algo
    );

    @Query(
        value = "SELECT * FROM name_index ni where ni.name = :name AND algo = :algo AND type = 's'",
        nativeQuery = true
    )
    public List<NameIndex> findBySNameAndAlgoParam(
        @Param("name") String name,
        @Param("algo") Long algo
    );
}
