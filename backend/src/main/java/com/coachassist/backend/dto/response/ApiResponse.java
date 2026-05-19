package com.coachassist.backend.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ApiResponse {

    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


}
