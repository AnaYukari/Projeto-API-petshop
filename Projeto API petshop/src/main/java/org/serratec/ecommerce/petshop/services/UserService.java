package org.serratec.ecommerce.petshop.services;

import org.serratec.ecommerce.petshop.entities.Role;
import org.serratec.ecommerce.petshop.entities.User;
import org.serratec.ecommerce.petshop.entities.UserDetailImpl;
import org.serratec.ecommerce.petshop.enuns.RoleEnum;
import org.serratec.ecommerce.petshop.records.CredenciaisLoginRecord;
import org.serratec.ecommerce.petshop.records.JwtTokenRecord;
import org.serratec.ecommerce.petshop.records.UserRecord;
import org.serratec.ecommerce.petshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;
    public JwtTokenRecord authenticateUser(CredenciaisLoginRecord credenciaisLoginRecord) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credenciaisLoginRecord.email(), credenciaisLoginRecord.senha());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        return new JwtTokenRecord(jwtTokenService.generateToken(userDetails));
    }

    public User createUser(UserRecord userRecord) {

        Set<String> strRoles = userRecord.role();
        List<Role> roles = new ArrayList<>();

        for(String strRole : strRoles) {
            Role role = new Role(RoleEnum.valueOf(strRole));
            roles.add(role);
        }

        User newUser = new User(userRecord.email(),
                encoder.encode(userRecord.senha()),
                roles
        );

        // Salva o novo usuário no banco de dados
        return userRepository.save(newUser);
    }
}
