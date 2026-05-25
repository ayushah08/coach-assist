package com.coachassist.backend.coaching.dto.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoachingRegisterRequest {

    @NotBlank(message = "Coaching Name Required")
    private String coachingName;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email Required")
    private String email;

    @NotBlank(message = "Password Required")
    @Size(min = 6,
            message = "Password must be 6 characters")
    private String password;

    @NotBlank(message = "Phone Number Required")
    private String phoneNumber;
}
