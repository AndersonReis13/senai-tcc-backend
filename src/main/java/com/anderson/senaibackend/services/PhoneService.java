package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.model.PhoneStatus;
import com.anderson.senaibackend.domain.repositories.PhoneRepository;
import com.anderson.senaibackend.domain.repositories.PhoneStatusRepository;
import com.anderson.senaibackend.dto.PhoneDto;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import com.anderson.senaibackend.mapper.PhoneMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneStatusRepository phoneStatusRepository;

    public PhoneService(PhoneRepository phoneRepository, PhoneStatusRepository phoneStatusRepository) {
        this.phoneRepository = phoneRepository;
        this.phoneStatusRepository = phoneStatusRepository;
    }

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone findById(Long id){
        return phoneRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("celular não encontrado"));
    }

    public Phone createPhone(PhoneDto dto) {

        var phoneStatusDb = phoneStatusRepository.findById(dto.status())
                .orElseThrow(()-> new ResourceNotFoundException("o status não foi encontrado"));

        PhoneStatus status = phoneStatusDb;
        return phoneRepository.save(PhoneMapper.toEntity(dto,status));
    }

    public Phone updatePhone(PhoneDto dto){

        var phoneStatusDb = phoneStatusRepository.findById(dto.status())
                .orElseThrow(() -> new ResourceNotFoundException("o status não foi encontrado"));

        PhoneStatus status = phoneStatusDb;
        return phoneRepository.save(PhoneMapper.toEntity(dto, status));
    }

    public void deletePhone(Long id){
        var phoneDb = phoneRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("O celular não foi encontrado"));
        Phone phone = phoneDb;
        phoneRepository.delete(phone);
    }
}
