package com.coachassist.backend.marks.repository;

import com.coachassist.backend.marks.entity.Marks;
import com.coachassist.backend.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Long> {
    List<Marks> findByStudent(Student student);

}