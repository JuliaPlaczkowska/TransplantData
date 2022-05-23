package com.edu.transplantdataapi.repository;

import com.edu.transplantdataapi.entity.user.Role;
import com.edu.transplantdataapi.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(ERole name);

}
