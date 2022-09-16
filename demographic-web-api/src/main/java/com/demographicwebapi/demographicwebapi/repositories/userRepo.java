package com.demographicwebapi.demographicwebapi.repositories;
import com.demographicwebapi.demographicwebapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface userRepo extends JpaRepository<User, Long> {

    public User findByFirstnameAndLastname(String firstname, String lastname);

}
    
