package com.spectrum.login.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spectrum.login.dto.JwtAuthenticationResponse;
import com.spectrum.login.dto.LoginRequest;
import com.spectrum.login.dto.RegisterRequest;
import com.spectrum.login.model.User;
import com.spectrum.login.repository.IUserRepository;
import com.spectrum.login.service.IAuthenticationService;
import com.spectrum.login.service.IJwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;

    public User register(RegisterRequest registerRequest) {
        User user = new User();

        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        // user.setRole(Role.USER);
        // newwly added
        user.setRole(registerRequest.getRole());
        user.setDepartment(registerRequest.getDepartment());
        user.setDivision(registerRequest.getDivision());
        user.setModule(registerRequest.getModule());
        user.setMiddleName(registerRequest.getMiddleName());
        user.setCompanyId(registerRequest.getCompanyId());
        user.setAccountId(registerRequest.getAccountId());
        user.setCompanyRole(registerRequest.getCompanyRole());
        user.setEmployeeId(registerRequest.getEmployeeId());
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword())
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        //new added
        jwtAuthenticationResponse.setFirstName(user.getFirstName());
       // jwtAuthenticationResponse.setRole(user.getRole());
        jwtAuthenticationResponse.setDivision(user.getDivision());
        jwtAuthenticationResponse.setDepartment(user.getDepartment());
        jwtAuthenticationResponse.setCompanyId(user.getCompanyId());
        jwtAuthenticationResponse.setAccountId(user.getAccountId());
        jwtAuthenticationResponse.setCompanyRole(user.getCompanyRole());
        jwtAuthenticationResponse.setEmployeeId(user.getEmployeeId());

        return jwtAuthenticationResponse;
    }

}
