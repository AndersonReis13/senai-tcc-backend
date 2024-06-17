package com.anderson.senaibackend.domain.repositories;

import com.anderson.senaibackend.domain.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus,Long> {
}
