package com.anderson.senaibackend.mapper;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.dto.ClientDto;

public class ClientMapper {
    public static Client toEntity(ClientDto dto, Phone phone){
        return new Client(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.cpf(),
                dto.address(),
                dto.phoneNumber(),
                phone);
    }
}
