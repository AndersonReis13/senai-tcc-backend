package com.anderson.senaibackend.exceptions.handle;

import com.anderson.senaibackend.dto.error.ErrorResponseDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizedExceptionHandle {

    @ExceptionHandler(BadRequestFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestFoundException ex, WebRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                new Date(),
                request.getDescription(false)
                );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND,
                new Date(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException notValid, WebRequest request){
        Map<String, String> erros = new HashMap<>();

        notValid.getBindingResult().getAllErrors().forEach(err -> {
            String field = ((FieldError) err).getField();
            String message = err.getDefaultMessage();
            erros.put(field, message);
        });

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
            Arrays.toString(erros.entrySet().toArray()),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST,
                new Date(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

}
