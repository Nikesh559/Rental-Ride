package com.example.model;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class Message {
    private Timestamp timestamp;
    private HttpStatus status;
    private String msg = "";
    private String error = "";
    Message() {}

    public Message(String msg, HttpStatus status) {
        this.msg = msg;
        this.status = status;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
