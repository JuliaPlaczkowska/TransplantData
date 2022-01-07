package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.Role;
import com.edu.transplantdataapi.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(ERole name);

}
