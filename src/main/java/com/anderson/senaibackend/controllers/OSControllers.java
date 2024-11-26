package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.OS;
import com.anderson.senaibackend.dto.OSDto;
import com.anderson.senaibackend.services.OSService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cos/os")
public class OSControllers {

    public final OSService osService;

    public OSControllers(OSService osService) {
        this.osService = osService;
    }

    @GetMapping
    public ResponseEntity<List<OS>> findAll(){
        return ResponseEntity.ok().body(osService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OS> findById(@RequestParam(value = "id") Long id){
       return ResponseEntity.ok().body(osService.findById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<OS> create(@Valid @RequestBody OSDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(osService.createOS(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<OS> update(@Valid @RequestBody OSDto dto){
        return ResponseEntity.ok().body(osService.updateOs(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Long id){
        osService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }



}
