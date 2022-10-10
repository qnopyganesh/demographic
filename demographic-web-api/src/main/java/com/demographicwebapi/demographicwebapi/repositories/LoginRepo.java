package com.demographicwebapi.demographicwebapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demographicwebapi.demographicwebapi.models.Login;

@Repository
public interface LoginRepo extends JpaRepository<Login, Long> {
    public Login findByUsername(String username);
}
