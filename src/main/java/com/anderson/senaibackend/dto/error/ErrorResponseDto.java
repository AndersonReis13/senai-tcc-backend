package com.anderson.senaibackend.dto.error;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponseDto {
    private Integer status;
    private String message;
    private String path;
    private HttpStatus httpCode;
    private Date timeInstant;

    public ErrorResponseDto(String message, Integer status,  HttpStatus httpCode, Date timeInstant,  String path) {
        this.message = message;
        this.status = status;
        this.httpCode = httpCode;
        this.timeInstant = timeInstant;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public Date getTimeInstant() {
        return timeInstant;
    }

    public void setTimeInstant(Date timeInstant) {
        this.timeInstant = timeInstant;
    }




}
