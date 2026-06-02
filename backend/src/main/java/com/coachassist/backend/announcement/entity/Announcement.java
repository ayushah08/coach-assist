package com.coachassist.backend.announcement.entity;

import com.coachassist.backend.coaching.entity.Coaching;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
@Getter
@Setter
@NoArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String message;

    private LocalDateTime createdAt;

    private LocalDate expiryDate;


    @ManyToOne
    @JoinColumn(name = "coaching_id")
    private Coaching coaching;

}