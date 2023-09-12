package org.api.auth.repository;


import org.api.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByTipo(String name);

    @Query("SELECT COUNT(id) FROM Role")
    int getRoleQuantity();

}
