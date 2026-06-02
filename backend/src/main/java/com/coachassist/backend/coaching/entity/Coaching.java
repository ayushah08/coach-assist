package com.coachassist.backend.coaching.entity;

import com.coachassist.backend.announcement.entity.Announcement;
import com.coachassist.backend.coaching.enums.ApprovalStatus;
import com.coachassist.backend.security.enums.Role;
import com.coachassist.backend.student.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "coachings")
@Getter
@Setter
@NoArgsConstructor
public class Coaching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String coachingName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String phoneNumber;

    @Column(unique = true)
    private String coachingCode;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;


    @OneToMany(
            mappedBy = "coaching",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Student> students;

    @OneToMany(
            mappedBy = "coaching",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Announcement> announcements;


}