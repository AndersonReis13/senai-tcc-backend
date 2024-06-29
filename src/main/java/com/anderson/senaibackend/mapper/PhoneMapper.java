package com.anderson.senaibackend.mapper;

import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.model.PhoneStatus;
import com.anderson.senaibackend.dto.PhoneDto;

public class PhoneMapper {

    public static Phone toEntity(PhoneDto dto, PhoneStatus status){
        return new Phone(
                dto.id(),
                dto.brand(),
                dto.model(),
                dto.problemDescription(),
                status
        );
    }
}
