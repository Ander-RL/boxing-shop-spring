package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.LoginResponseDto;
import com.boxing.shop.react.dto.PostApplicationUserDto;
import com.boxing.shop.react.entity.ApplicationUser;
import com.boxing.shop.react.entity.Role;
import com.boxing.shop.react.mapper.IApplicationUserMapper;
import com.boxing.shop.react.repository.IRoleRepository;
import com.boxing.shop.react.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    private AuthenticationManager authenticationManager;

    private TokenService tokenService;

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

    public LoginResponseDto loginUser(String username, String password){

        try{ // Find user & check password
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(userRepository.findByUsername(username).get(), token);

        } catch(AuthenticationException e){
            return new LoginResponseDto(null, "");
        }
    }
}
