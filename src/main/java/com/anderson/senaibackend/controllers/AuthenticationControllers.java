package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.enums.TypeEmployee;
import com.anderson.senaibackend.domain.repositories.EmployeeRepository;
import com.anderson.senaibackend.dto.AuthenticationDto;
import com.anderson.senaibackend.dto.EmployeeDto;
import com.anderson.senaibackend.dto.EmployeeRegisterDto;
import com.anderson.senaibackend.dto.LoginResponseDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(value = "cos/auth")
public class AuthenticationControllers {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final TokenService tokenService;

  private Logger logger = Logger.getLogger(Employee.class.getName());

    @Autowired
    public AuthenticationControllers(AuthenticationManager authenticationManager, EmployeeRepository employeeRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto dto){
        try {
            logger.info("Entrando no login");

            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);

            var token = tokenService.generateToken((Employee) auth.getPrincipal());


            return ResponseEntity.ok(new LoginResponseDto(token));

        } catch (AuthenticationException e) {
            throw new BadRequestFoundException("Usuario não encontrado!");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeDto dto){
        logger.info("entrando no register");

        if(!employeeRepository.existsByEmail(dto.email())){
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Employee employee = new Employee(dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                encryptedPassword,
                dto.phoneNumber(),
                TypeEmployee.valueOf(dto.typeEmployee().toUpperCase()));

        Employee newEmployee = employeeRepository.save(employee);

        logger.info("employee salvo " + newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }
}
