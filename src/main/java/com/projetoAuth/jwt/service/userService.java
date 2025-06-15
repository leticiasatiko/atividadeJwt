package com.projetoAuth.jwt.service;

import com.projetoAuth.jwt.model.userModel;
import com.projetoAuth.jwt.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userService implements UserDetailsService {

    @Autowired
    private userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        userModel user = userRepository.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + login);
        }
        return user;
    }
}
