package com.anderson.senaibackend.domain.repositories;


import com.anderson.senaibackend.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ClientRepository extends JpaRepository<Client, Long> {
}
