package com.edu.transplantdataapi.repository.jpa;


import com.edu.transplantdataapi.entity.Role;
import com.edu.transplantdataapi.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
