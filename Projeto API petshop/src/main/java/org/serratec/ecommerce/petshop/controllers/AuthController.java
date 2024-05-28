package org.serratec.ecommerce.petshop.controllers;

import org.serratec.ecommerce.petshop.entities.User;
import org.serratec.ecommerce.petshop.records.JwtTokenRecord;
import org.serratec.ecommerce.petshop.records.CredenciaisLoginRecord;
import org.serratec.ecommerce.petshop.records.UserRecord;
import org.serratec.ecommerce.petshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @PostMapping ("/signin")
    public ResponseEntity<JwtTokenRecord> login(@RequestBody CredenciaisLoginRecord credenciaisLoginRecord){
        JwtTokenRecord jwtToken = userService.authenticateUser(credenciaisLoginRecord);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
    @PostMapping ("/signup")
    public ResponseEntity<User> cadastro(@RequestBody UserRecord userRecord){
        return new ResponseEntity<>(userService.createUser(userRecord), HttpStatus.CREATED);
    }

}
