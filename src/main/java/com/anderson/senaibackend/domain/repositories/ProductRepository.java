package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

 public interface ProductRepository extends JpaRepository<Product, Long> {
}
