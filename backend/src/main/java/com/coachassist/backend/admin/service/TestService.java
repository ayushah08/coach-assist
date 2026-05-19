package com.coachassist.backend.admin.service;

import com.coachassist.backend.dto.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public ApiResponse testApi(){
        return new ApiResponse(
                "Coach Assist Running Succesfully !! " ,
                true
        );

    }
}
