package com.coachassist.backend.announcement.controller;

import com.coachassist.backend.announcement.dto.request.AnnouncementRequest;
import com.coachassist.backend.announcement.dto.response.AnnouncementResponse;
import com.coachassist.backend.announcement.service.AnnouncementService;
import com.coachassist.backend.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/create")
    public ApiResponse createAnnouncement(
            @RequestBody AnnouncementRequest request
    ) {

        return announcementService
                .createAnnouncement(request);
    }

    @GetMapping("/active/{coachingCode}")
    public List<AnnouncementResponse>
    getActiveAnnouncements(

            @PathVariable String coachingCode
    ) {

        return announcementService
                .getActiveAnnouncements(
                        coachingCode
                );
    }
}