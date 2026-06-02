package com.coachassist.backend.announcement.repository;

import com.coachassist.backend.announcement.entity.Announcement;
import com.coachassist.backend.coaching.entity.Coaching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByCoachingAndExpiryDateAfter(
            Coaching coaching,
            LocalDate date
    );

}