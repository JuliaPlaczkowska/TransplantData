package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByEmail(String email);
}
