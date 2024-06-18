package com.anderson.senaibackend.exceptions.handle;

import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustumizedExceptionHandle {

     @ExceptionHandler(Exception.class)
    public final ResponseEntity<ModelExceptions> handleAllExcepttions(Exception ex){
       ModelExceptions modelExceptions = new ModelExceptions(
               ex.getMessage(),
               HttpStatus.valueOf(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR)),
               Integer.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
               new Date());

       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modelExceptions);
    }

    @ExceptionHandler(BadRequestFoundException.class)
    public final ResponseEntity<ModelExceptions> badRequestExceptionHandle(BadRequestFoundException bad){
        ModelExceptions modelExceptions = new ModelExceptions(
                bad.getMessage(),
                HttpStatus.valueOf(String.valueOf(HttpStatus.BAD_REQUEST)),
                Integer.valueOf(HttpStatus.BAD_REQUEST.value()),
                new Date());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelExceptions);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ModelExceptions> resourceNotFoundExceptionHandle(ResourceNotFoundException resource){
        ModelExceptions modelExceptions = new ModelExceptions(
                resource.getMessage(),
                HttpStatus.valueOf(String.valueOf(HttpStatus.NOT_FOUND)),
                Integer.valueOf(HttpStatus.NOT_FOUND.value()),
                new Date());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modelExceptions);
    }
}
