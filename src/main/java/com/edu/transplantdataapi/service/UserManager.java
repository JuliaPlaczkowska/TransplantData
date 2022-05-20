package com.edu.transplantdataapi.service;

import com.edu.transplantdataapi.dto.user.UserDto;
import com.edu.transplantdataapi.entity.user.Role;
import com.edu.transplantdataapi.entity.user.User;
import com.edu.transplantdataapi.enums.ERole;
import com.edu.transplantdataapi.exceptions.CredentialAlreadyTakenException;
import com.edu.transplantdataapi.exceptions.InvalidRoleNameException;
import com.edu.transplantdataapi.exceptions.UserNotFoundException;
import com.edu.transplantdataapi.repository.RoleRepo;
import com.edu.transplantdataapi.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class UserManager implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;


    public UserDto addRole(String username, String roleName) {
        User user = findUserByUsername(username);
        Role role = findRoleByRoleName(roleName);
        user.addRole(role);
        return mapper.map(user, UserDto.class);
    }

    public UserDto removeRole(String username, String roleName) {
        User user = findUserByUsername(username);
        Role role = findRoleByRoleName(roleName);
        user.removeRole(role);
        return mapper.map(user, UserDto.class);
    }

    public UserDto registerUser(UserDto userDto) {
        if (existsByUsername(userDto.getUsername())) {
            throw new CredentialAlreadyTakenException("username");
        }
        if (existsByEmail(userDto.getEmail())) {
            throw new CredentialAlreadyTakenException("email");
        }
        return save(userDto);
    }

    private UserDto save(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        Set<Role> rolesSet = new HashSet<>();

        if (userDto.getRoles() == null) {
            Role userRole = roleRepo.findByName(ERole.ROLE_USER);
            rolesSet.add(userRole);
        }
        user.setRoles(rolesSet);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return mapper.map(userRepo.save(user), UserDto.class);
    }

    private boolean validRoleName(String roleName) {
        return roleName.equals(ERole.ROLE_ADMIN.toString())
                || roleName.equals(ERole.ROLE_DOCTOR.toString())
                || roleName.equals(ERole.ROLE_USER.toString());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    private boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    private boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    private User findUserByUsername(String username) {
        User user;
        Optional<User> optional =
                Optional.ofNullable(userRepo.findByUsername(username));
        if (optional.isPresent()) {
            user = optional.get();
            return user;
        } else throw new UserNotFoundException(username);
    }

    private Role findRoleByRoleName(String roleName) {
        Role role;
        if (validRoleName(roleName)) {
            role = roleRepo.findByName(ERole.valueOf(roleName));
        } else throw new InvalidRoleNameException(roleName);
        return role;
    }

    public UserDto findByUsername(String username) {
        User user = findUserByUsername(username);
        return mapper.map(user, UserDto.class);
    }
}
