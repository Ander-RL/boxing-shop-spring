package com.boxing.shop.react.service;

import com.boxing.shop.react.dto.LoginResponseDto;
import com.boxing.shop.react.dto.PostApplicationUserDto;
import com.boxing.shop.react.dto.PostRegistrationUserDto;
import com.boxing.shop.react.dto.RegistrationUserResponseDto;
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

    public RegistrationUserResponseDto registerUser(PostRegistrationUserDto postRegistrationUserDto) {

        RegistrationUserResponseDto registrationResponse = new RegistrationUserResponseDto();

        if(!isInputFieldValid(postRegistrationUserDto, registrationResponse)) return registrationResponse;

        String encodedPassword = passwordEncoder.encode(postRegistrationUserDto.getPassword());
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(NoSuchElementException::new);

        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        postRegistrationUserDto.setPassword(encodedPassword);

        ApplicationUser applicationUser = applicationUserMapper.dtoToEntity(postRegistrationUserDto);
        applicationUser.setAuthorities(authorities);

        registrationResponse.setUsername(applicationUser.getUsername());
        registrationResponse.setEmail(applicationUser.getEmail());
        registrationResponse.setFirstName(applicationUser.getFirstName());
        registrationResponse.setSecondName(applicationUser.getSecondName());

        if(!userExists(applicationUser)){
            userRepository.save(applicationUser);
            registrationResponse.setMessage("User created");
            registrationResponse.setStatusCode(201);
        } else {
            registrationResponse.setMessage("User already exists");
            registrationResponse.setStatusCode(409);
        }

        return registrationResponse;
    }

    private boolean isInputFieldValid(PostRegistrationUserDto postRegistrationUserDto, RegistrationUserResponseDto registrationResponse) {
        if(!postRegistrationUserDto.getPassword().equals(postRegistrationUserDto.getConfirmPassword())) {
            registrationResponse.setMessage("Password and confirm password are not equals.");
            registrationResponse.setStatusCode(401);
            return false;
        }

        return true;
    }

    private boolean userExists(ApplicationUser applicationUser) {
        return userRepository.findByEmail(applicationUser.getEmail()).isPresent() || userRepository.findByUsername(applicationUser.getUsername()).isPresent();
    }

    public LoginResponseDto loginUser(PostApplicationUserDto credentials){

        try{ // Find user & check password
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            ApplicationUser user = userRepository.findByUsername(credentials.getUsername()).get();

            return new LoginResponseDto(user.getUsername(),
                                        user.getFirstName(),
                                        user.getSecondName(),
                                        user.getEmail(),
                                        "User logged in successfully",
                                        201,
                                        token);

        } catch(AuthenticationException e){
            return new LoginResponseDto(null,
                    null,
                    null,
                    null,
                    "Wrong login credentials",
                    401,
                    "");
            }
    }
}
