package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.entity.user.Role;
import com.edu.transplantdataapi.enums.ERole;
import com.edu.transplantdataapi.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleManager {

    private RoleRepo roleRepo;

    @Autowired
    public RoleManager(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role save(Role role){
        return roleRepo.save(role);
    }

    public Role findByName(ERole name) {

        Role role = roleRepo.findByName(name);
        if(role == null){
            throw new UsernameNotFoundException("Role not found in the database");
        }
        return role;
    }
}
