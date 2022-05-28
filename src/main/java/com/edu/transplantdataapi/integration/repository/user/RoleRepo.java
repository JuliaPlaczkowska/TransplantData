package com.edu.transplantdataapi.integration.repository.user;

import com.edu.transplantdataapi.entities.user.Role;
import com.edu.transplantdataapi.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(ERole name);

}
