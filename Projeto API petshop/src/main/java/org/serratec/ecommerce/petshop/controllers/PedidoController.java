package org.serratec.ecommerce.petshop.controllers;

import java.util.List;

import org.serratec.ecommerce.petshop.dtos.PedidoResumidoDto;
import org.serratec.ecommerce.petshop.entities.Pedido;
import org.serratec.ecommerce.petshop.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoResumidoDto>>findAll(){
		return new ResponseEntity<>(pedidoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<PedidoResumidoDto>findById(@PathVariable Integer id){
		PedidoResumidoDto pedido = pedidoService.findById(id);
		if (pedido == null) {
			return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<Pedido>save(@RequestBody Pedido pedido){
		return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Pedido> update (@RequestBody Pedido pedido){
		return new ResponseEntity<>(pedidoService.update(pedido), HttpStatus.OK);
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<Pedido> delete(@PathVariable Integer id){
		Pedido pedido = pedidoService.findById(id);
		if (pedido == null) {
			return new ResponseEntity<>(pedido,HttpStatus.NOT_FOUND);
		} else {
			pedidoService.delete(id);
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		}
	}
}
