package com.coachassist.backend.coaching.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoachingLoginResponse {

    private String token;

    private String coachingName;

    private String coachingCode;
}