package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.OS;
import com.anderson.senaibackend.domain.repositories.ClientRepository;
import com.anderson.senaibackend.domain.repositories.OsRepository;
import com.anderson.senaibackend.dto.OSDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OSService {

    private final OsRepository osRepository;
    private final ClientRepository clientRepository;



    public OSService(OsRepository osRepository, ClientRepository clientRepository) {
        this.osRepository = osRepository;
        this.clientRepository = clientRepository;
    }


    public List<OS> findAll(){
        return osRepository.findAll();
    }

    public OS findById(Long id){
        return osRepository.findById(id)
                .orElseThrow(()-> new BadRequestFoundException("OS não encontrada"));
    }

    public OS createOS(OSDto dto){

        return osRepository.save(dto.toEntity(dto));
    }

    public OS updateOs(OSDto dto){


        return osRepository.save(dto.toEntity(dto));
    }

    public void delete(Long id){
        osRepository.delete(findById(id));
    }

}
