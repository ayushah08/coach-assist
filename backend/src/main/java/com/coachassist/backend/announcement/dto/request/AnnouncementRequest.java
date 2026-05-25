package com.coachassist.backend.announcement.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AnnouncementRequest {

    private String coachingCode;

    private LocalDate expiryDate;

    private String title;

    private String message;
}