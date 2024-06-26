package com.boxing.shop.react.service;

import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("[UserService][service][loadUserByUsername] username: " + username);

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " user is not valid"));
    }

    public ApplicationUser loadApplicationUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " user is not valid"));
    }

    public UserDetails updateUser(ApplicationUser user) throws UsernameNotFoundException {

        return userRepository.save(user);
    }
}
