package com.openclassrooms.library.dao;


import com.openclassrooms.library.entity.ERole;
import com.openclassrooms.library.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
