package com.coachassist.backend.parent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParentLoginResponse {

    private String token;

    private String studentName;

    private String parentName;

    private String coachingName;
}