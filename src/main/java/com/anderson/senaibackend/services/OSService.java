package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.OS;
import com.anderson.senaibackend.domain.repositories.OsRepository;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OSService {

    private final OsRepository osRepository;


    public OSService(OsRepository osRepository) {
        this.osRepository = osRepository;
    }


    public List<OS> findAll(){
        return osRepository.findAll();
    }

    public OS findById(Long id){
        return osRepository.findById(id)
                .orElseThrow(()-> new BadRequestFoundException("OS não encontrada"));
    }

    public OS createOS(){
        return null;
    }

}
