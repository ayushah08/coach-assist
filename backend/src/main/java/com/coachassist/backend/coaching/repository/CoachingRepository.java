package com.coachassist.backend.coaching.repository;

import com.coachassist.backend.coaching.entity.Coaching;
import com.coachassist.backend.coaching.enums.ApprovalStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachingRepository extends JpaRepository<Coaching, Long> {
    Optional<Coaching> findByEmail(String email);

    Optional<Coaching> findByCoachingCode(String coachingCode);
    Page<Coaching>
    findByCoachingNameContainingIgnoreCase(

            String keyword,

            Pageable pageable
    );

    List<Coaching> findByApprovalStatus(
            ApprovalStatus approvalStatus
    );
}