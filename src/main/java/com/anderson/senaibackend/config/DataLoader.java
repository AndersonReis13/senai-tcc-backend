package com.anderson.senaibackend.config;

import com.anderson.senaibackend.domain.model.PhoneStatus;
import com.anderson.senaibackend.domain.model.ProductStatus;
import com.anderson.senaibackend.domain.model.TypeEmployee;
import com.anderson.senaibackend.domain.repositories.PhoneStatusRepository;
import com.anderson.senaibackend.domain.repositories.ProductStatusRepository;
import com.anderson.senaibackend.domain.repositories.TypeEmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    public final PhoneStatusRepository phoneStatusRepository;
    public final ProductStatusRepository productStatusRepository;
    public final TypeEmployeeRepository typeEmployeeRepository;

    public DataLoader(PhoneStatusRepository phoneStatusRepository, ProductStatusRepository productStatusRepository, TypeEmployeeRepository typeEmployeeRepository) {
        this.phoneStatusRepository = phoneStatusRepository;
        this.productStatusRepository = productStatusRepository;
        this.typeEmployeeRepository = typeEmployeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(PhoneStatus.Enum.values())
                .forEach(phoneStatus -> phoneStatusRepository.save(phoneStatus.get()));

        Arrays.stream(ProductStatus.Enum.values())
                .forEach(productStatus -> productStatusRepository.save(productStatus.get()));

        Arrays.stream(TypeEmployee.Enum.values())
                .forEach(typeEmployee -> typeEmployeeRepository.save(typeEmployee.get()));
    }

}
