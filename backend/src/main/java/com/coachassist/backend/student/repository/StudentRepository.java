package com.coachassist.backend.student.repository;

import com.coachassist.backend.student.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByStudentCode(
            String studentCode
    );

    Page<Student> findByStudentNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );
}