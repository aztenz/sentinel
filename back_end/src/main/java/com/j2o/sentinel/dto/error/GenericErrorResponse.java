package com.j2o.sentinel.dto.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}