package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OsRepository extends JpaRepository<OS, Long> {
}
