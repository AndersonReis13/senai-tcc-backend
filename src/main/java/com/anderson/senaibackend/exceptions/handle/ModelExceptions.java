package com.anderson.senaibackend.exceptions.handle;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ModelExceptions {


    private String msg;
    private String detail;
    private HttpStatus httpStatus;
    private Long httpCode;
    private Date timeInstant;

    public ModelExceptions(String message, String description, HttpStatus internalServerError, HttpStatus httpStatus, Date timeInstant){}

    public ModelExceptions(String msg, String detail, HttpStatus httpStatus, Long httpCode, Date timeInstant) {
        this.msg = msg;
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

    public Long getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Long httpCode) {
        this.httpCode = httpCode;
    }

    public Date getTimeInstant() {
        return timeInstant;
    }

    public void setTimeInstant(Date timeInstant) {
        this.timeInstant = timeInstant;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
