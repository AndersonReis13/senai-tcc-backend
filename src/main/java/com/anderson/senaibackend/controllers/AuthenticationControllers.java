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
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticationDto dto) {
        try {
            logger.info("Entrando no login");
    
            var userNamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
            var auth = this.authenticationManager.authenticate(userNamePassword);
    
            var employee = (Employee) auth.getPrincipal(); // Obtém o Employee autenticado
            var token = tokenService.generateToken(employee);
    
            // Cria o DTO do Employee com base nos dados do funcionário autenticado
            var employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getCpf(),
                employee.getPassword(), // Certifique-se de que você deseja expor a senha!
                employee.getPhoneNumber(),
                employee.getTypeEmployeeId().toString().toUpperCase()
            );
    
            // Cria o DTO de resposta com o token e os dados do funcionário
            var responseDto = new LoginResponseDto(token, employeeDto);
    
            return ResponseEntity.ok(responseDto);
    
        } catch (AuthenticationException e) {
            throw new BadRequestFoundException("Usuário não encontrado!");
        }
    }
    

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody @Valid EmployeeDto dto){
        logger.info("entrando no register");

        if(employeeRepository.existsByEmail(dto.email())){
            throw new BadRequestFoundException("Já existe um usuario com esse email");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        Employee employee = new Employee(dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.cpf(),
                encryptedPassword,
                dto.phoneNumber(),
                TypeEmployee.valueOf(dto.typeEmployee().toUpperCase()));

        Employee newEmployee = employeeRepository.save(employee);

        logger.info("employee salvo " + newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }
}
