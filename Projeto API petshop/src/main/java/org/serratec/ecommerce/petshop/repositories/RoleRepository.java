package org.serratec.ecommerce.petshop.repositories;

import org.serratec.ecommerce.petshop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
