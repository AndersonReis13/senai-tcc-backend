package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.PhoneStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhoneStatusRepository extends JpaRepository<PhoneStatus, Long> {
}
