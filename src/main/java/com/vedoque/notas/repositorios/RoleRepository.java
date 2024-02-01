package com.vedoque.notas.repositorios;

import com.vedoque.notas.modelo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
