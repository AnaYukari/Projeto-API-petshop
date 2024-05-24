package org.serratec.ecommerce.petshop.repositories;

import org.serratec.ecommerce.petshop.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
