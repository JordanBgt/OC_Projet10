package com.openclassrooms.library.service;

import com.openclassrooms.library.dao.RoleRepository;
import com.openclassrooms.library.entity.ERole;
import com.openclassrooms.library.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to manage roles
 *
 * @see Role
 * @see RoleRepository
 */
@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> findByName(ERole role) {
        return roleRepository.findByName(role);
    }
}
