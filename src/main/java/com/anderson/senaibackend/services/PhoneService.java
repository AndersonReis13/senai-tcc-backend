package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.model.enums.PhoneStatus;
import com.anderson.senaibackend.domain.repositories.PhoneRepository;
import com.anderson.senaibackend.dto.PhoneDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone findById(Long id){
        return phoneRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("celular não encontrado"));
    }

    public Phone addPhone(PhoneDto dto) {
        if(dto.id() != null){
            throw new BadRequestFoundException("O id do client tem que ser null");
        }
        if(dto.phoneStatus() == null){
            throw new BadRequestFoundException("O status não pode vim vazio");
        }
        checkEnumInField(dto.phoneStatus());
        return phoneRepository.save(dto.toEntity(dto));
    }

    public Phone modifyPhone(PhoneDto dto){
        if(dto.phoneStatus() == null){
            throw new BadRequestFoundException("O status não pode vim vazio");
        }

        checkEnumInField(dto.phoneStatus());

        return phoneRepository.save(dto.toEntity(dto));
    }

    public void removePhone(Long id){
        var phoneDb = phoneRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("O celular não foi encontrado"));

        phoneRepository.delete(phoneDb);
    }

    public void checkEnumInField(String status){
        if(!PhoneStatus.existsEnum(status)){
            throw new BadRequestFoundException("Não existe este tipo de status de celular");
        }
    }


}
