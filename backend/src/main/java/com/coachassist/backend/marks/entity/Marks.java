package com.coachassist.backend.marks.entity;

import com.coachassist.backend.student.entity.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "marks")
@Getter
@Setter
@NoArgsConstructor
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String examName;

    private double marksObtained;

    private double totalMarks;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}