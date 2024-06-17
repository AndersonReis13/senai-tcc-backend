package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.TypeEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeEmployeeRepository extends JpaRepository<TypeEmployee, Long> {
}
