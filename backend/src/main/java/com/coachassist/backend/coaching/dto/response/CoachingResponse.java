package com.coachassist.backend.coaching.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoachingResponse {


    private String coachingName;

    private String coachingCode;

    private String email;

    private String phoneNumber;
}