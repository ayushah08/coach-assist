package com.coachassist.backend.coaching.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoachingLoginRequest {

    private String email;

    private String password;
}