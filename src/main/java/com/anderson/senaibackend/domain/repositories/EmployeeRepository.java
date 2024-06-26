package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;




public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    Employee findByEmail(String email);
    boolean existsByEmail(String email);
}
