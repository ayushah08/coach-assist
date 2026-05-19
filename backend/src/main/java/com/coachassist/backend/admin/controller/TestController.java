package com.coachassist.backend.admin.controller;

import com.coachassist.backend.admin.service.TestService;
import com.coachassist.backend.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    @GetMapping("/api/test")
    public ApiResponse testApi(){

        return testService.testApi();

}


}