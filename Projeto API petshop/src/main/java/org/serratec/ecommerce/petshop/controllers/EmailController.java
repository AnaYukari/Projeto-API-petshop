package org.serratec.ecommerce.petshop.controllers;

import org.serratec.ecommerce.petshop.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    PedidoService pedidoService;
    @GetMapping("/relatorioPedido/{id}")
    public ResponseEntity<String> enviarEmail(@PathVariable Integer id){
        return new ResponseEntity<>(pedidoService.enviarEmail(id), HttpStatus.OK);
    }
}
