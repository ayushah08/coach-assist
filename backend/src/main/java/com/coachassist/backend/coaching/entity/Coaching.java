package com.coachassist.backend.coaching.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private String password;

    private String phoneNumber;

    @Column(unique = true)
    private String coachingCode;
}