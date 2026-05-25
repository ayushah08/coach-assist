package com.coachassist.backend.parent.controller;

import com.coachassist.backend.parent.dto.request.ParentLoginRequest;
import com.coachassist.backend.parent.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping("/login")
    public Object loginParent(
            @RequestBody ParentLoginRequest request
    ) {

        return parentService.loginParent(request);
    }
}