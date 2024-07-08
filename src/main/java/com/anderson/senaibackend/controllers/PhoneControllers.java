package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.dto.PhoneDto;
import com.anderson.senaibackend.services.PhoneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cos/phone")
public class PhoneControllers {
    private final PhoneService phoneService;

    public PhoneControllers(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Phone>> findAll(){
        return ResponseEntity.ok().body(phoneService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Phone> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(phoneService.findById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Phone>createPhone(@Valid @RequestBody PhoneDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneService.addPhone(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Phone> updatePhone(@Valid @RequestBody PhoneDto dto){
        return ResponseEntity.ok().body(phoneService.modifyPhone(dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id){
        phoneService.removePhone(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
