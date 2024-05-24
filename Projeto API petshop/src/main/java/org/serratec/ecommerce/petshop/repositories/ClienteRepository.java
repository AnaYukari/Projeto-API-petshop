package org.serratec.ecommerce.petshop.repositories;

import org.serratec.ecommerce.petshop.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
