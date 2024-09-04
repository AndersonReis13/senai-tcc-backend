package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.repositories.EmployeeRepository;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenrizationService implements UserDetailsService {

    public final EmployeeRepository employeeRepository;

    @Autowired
    public AuthenrizationService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return employeeRepository.findByEmail(email);
    }
}
