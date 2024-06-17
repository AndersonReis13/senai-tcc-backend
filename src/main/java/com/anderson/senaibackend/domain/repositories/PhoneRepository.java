package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
