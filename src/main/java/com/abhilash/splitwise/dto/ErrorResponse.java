package com.abhilash.splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    public ErrorResponse(String message,int status,LocalDateTime timeStamp){
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }
    private String message;
    private int status;
    private LocalDateTime timeStamp;
}
