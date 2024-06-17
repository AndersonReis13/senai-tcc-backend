package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

 interface ProductRepository extends JpaRepository<Product, Long> {
}
