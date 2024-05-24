package org.serratec.ecommerce.petshop.repositories;

import org.serratec.ecommerce.petshop.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
