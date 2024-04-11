package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.PostApplicationUserDto;
import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Role;
import com.boxing.shop.react.mapper.IApplicationUserMapper;
import com.boxing.shop.react.repository.IRoleRepository;
import com.boxing.shop.react.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final IApplicationUserMapper applicationUserMapper;

    private final PasswordEncoder passwordEncoder;

    public ApplicationUser registerUser(PostApplicationUserDto postApplicationUserDto) {

        String encodedPassword = passwordEncoder.encode(postApplicationUserDto.getPassword());
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(NoSuchElementException::new);

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        postApplicationUserDto.setPassword(encodedPassword);

        ApplicationUser applicationUser = applicationUserMapper.dtoToEntity(postApplicationUserDto);
        applicationUser.setAuthorities(authorities);

        return userRepository.save(applicationUser);
    }
}
