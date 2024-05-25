package org.serratec.ecommerce.petshop.controllers;

import java.util.List;

import org.serratec.ecommerce.petshop.dtos.ItemPedidoDto;
import org.serratec.ecommerce.petshop.entities.ItemPedido;
import org.serratec.ecommerce.petshop.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens-pedidos")
public class ItemPedidoController {
	
	@Autowired
	ItemPedidoService itemPedidoService;
	
	@GetMapping
	public ResponseEntity<List<ItemPedidoDto>>findAll(){
		return new ResponseEntity<>(itemPedidoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<ItemPedidoDto>findById(@PathVariable Integer id){
		ItemPedidoDto itemPedido = itemPedidoService.findById(id);
		if (itemPedido == null) {
			return new ResponseEntity<>(itemPedido, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(itemPedido, HttpStatus.OK);
		}
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido>save(@RequestBody ItemPedido itemPedido){
		return new ResponseEntity<>(itemPedidoService.save(itemPedido), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ItemPedido> update (@RequestBody ItemPedido itemPedido){
		return new ResponseEntity<>(itemPedidoService.update(itemPedido), HttpStatus.OK);
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<ItemPedido> delete(@PathVariable Integer id){
		ItemPedido itemPedido = itemPedidoService.findByIdCompleto(id);
		if (itemPedido == null) {
			return new ResponseEntity<>(itemPedido,HttpStatus.NOT_FOUND);
		} else {
			itemPedidoService.delete(id);
			return new ResponseEntity<>(itemPedido, HttpStatus.OK);
		}
	}
}
