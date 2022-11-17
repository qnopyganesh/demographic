package com.demographicwebapi.demographicwebapi.repositories;
import com.demographicwebapi.demographicwebapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface userRepo extends JpaRepository<User, Long> {

    public List<User> findByFirstnameAndLastname(String firstname, String lastname);

    public User findByEmailId(String emailId);

    public User findByPhonenumber(String phonenumber);

    public List<User> findByDob(String dob);


}
    
