package org.serratec.ecommerce.petshop.services;

import org.serratec.ecommerce.petshop.entities.User;
import org.serratec.ecommerce.petshop.entities.UserDetailImpl;
import org.serratec.ecommerce.petshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado")
        );

        return new UserDetailImpl(user);
    }
}
