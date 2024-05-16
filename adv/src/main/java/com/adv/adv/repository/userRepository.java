package com.adv.adv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adv.adv.model.*;

public interface userRepository extends JpaRepository<User,Long> {
  
         User findById(int id);
        Optional<User> findByEmail(String email);
        Optional<User> findByUsername(String username);
        List<User> findByUserType(User.UserType userType);
        User deleteById(int id);
        Boolean existsByEmail(String email);
        Boolean existsByUsername(String username);
        Boolean existsByUserType(User.UserType userType);

    }
    
