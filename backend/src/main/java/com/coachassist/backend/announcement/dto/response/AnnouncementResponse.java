package com.coachassist.backend.announcement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnnouncementResponse {

    private Long id;

    private String title;

    private String message;

    private String createdAt;
}