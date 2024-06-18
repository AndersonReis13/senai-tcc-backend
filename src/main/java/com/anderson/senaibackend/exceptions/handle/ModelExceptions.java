package com.anderson.senaibackend.exceptions.handle;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ModelExceptions {


    private String detail;
    private HttpStatus httpStatus;
    private Integer httpCode;
    private Date timeInstant;

    public ModelExceptions(){}

    public ModelExceptions(String detail, HttpStatus httpStatus, Integer httpCode, Date timeInstant) {
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.httpCode = httpCode;
        this.timeInstant = timeInstant;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public Date getTimeInstant() {
        return timeInstant;
    }

    public void setTimeInstant(Date timeInstant) {
        this.timeInstant = timeInstant;
    }

}
