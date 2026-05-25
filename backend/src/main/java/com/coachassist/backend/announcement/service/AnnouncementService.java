package com.coachassist.backend.announcement.service;

import com.coachassist.backend.announcement.dto.request.AnnouncementRequest;
import com.coachassist.backend.announcement.dto.response.AnnouncementResponse;
import com.coachassist.backend.announcement.entity.Announcement;
import com.coachassist.backend.announcement.repository.AnnouncementRepository;
import com.coachassist.backend.coaching.entity.Coaching;
import com.coachassist.backend.coaching.repository.CoachingRepository;
import com.coachassist.backend.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final CoachingRepository coachingRepository;

   public ApiResponse createAnnouncement(
            AnnouncementRequest request
    ) {

        Optional<Coaching> optionalCoaching =
                coachingRepository.findByCoachingCode(
                        request.getCoachingCode()
                );

        if (optionalCoaching.isEmpty()) {

            return new ApiResponse(
                    "Coaching Not Found",
                    false
            );
        }

        Coaching coaching =
                optionalCoaching.get();

        Announcement announcement =
                new Announcement();

        announcement.setTitle(
                request.getTitle()
        );

        announcement.setMessage(
                request.getMessage()
        );

        announcement.setCreatedAt(
                LocalDateTime.now()
        );

        announcement.setExpiryDate(
                request.getExpiryDate()
        );

        announcement.setCoaching(
                coaching
        );

        announcementRepository.save(
                announcement
        );

        return new ApiResponse(
                "Announcement Created Successfully",
                true
        );
    }

    public List<AnnouncementResponse> getActiveAnnouncements(
            String coachingCode
    ) {

        Optional<Coaching> optionalCoaching =
                coachingRepository.findByCoachingCode(
                        coachingCode
                );

        if (optionalCoaching.isEmpty()) {

            return List.of();
        }

        Coaching coaching =
                optionalCoaching.get();

        List<Announcement> announcements =
                announcementRepository
                        .findByCoachingAndExpiryDateAfter(
                                coaching,
                                LocalDate.now()
                        );

        return announcements.stream()
                .map(announcement ->
                        new AnnouncementResponse(
                                announcement.getId(),
                                announcement.getTitle(),
                                announcement.getMessage(),
                                announcement.getCreatedAt().toString()
                        )
                )
                .toList();
    }
}