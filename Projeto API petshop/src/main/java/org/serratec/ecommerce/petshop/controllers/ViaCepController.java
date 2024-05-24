package org.serratec.ecommerce.petshop.controllers;

import org.serratec.ecommerce.petshop.entities.Endereco;
import org.serratec.ecommerce.petshop.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ViaCepController {

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public Endereco getCepInfo(@PathVariable String cep) {
        return viaCepService.getCepInfo(cep);
    }
}
