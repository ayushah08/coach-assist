package com.coachassist.backend.student.entity;

import com.coachassist.backend.attendance.entity.Attendance;
import com.coachassist.backend.coaching.entity.Coaching;
import com.coachassist.backend.marks.entity.Marks;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    @Column(unique = true)
    private String studentCode;

    private String className;

    private String parentName;

    private String parentPhone;

    @ManyToOne
    @JoinColumn(name = "coaching_id")
    private Coaching coaching;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attendance> attendanceList;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Marks> marksList;
}