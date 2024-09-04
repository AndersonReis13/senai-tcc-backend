package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
