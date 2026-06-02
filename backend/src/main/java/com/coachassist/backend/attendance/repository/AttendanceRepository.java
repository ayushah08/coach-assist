package com.coachassist.backend.attendance.repository;

import com.coachassist.backend.attendance.entity.Attendance;
import com.coachassist.backend.attendance.enums.AttendanceStatus;
import com.coachassist.backend.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudent(Student student);
    long countByStudentAndStatus(
            Student student,
            AttendanceStatus status
    );
    long countByAttendanceDateAndStatus(
            LocalDate attendanceDate,
            AttendanceStatus status
    );


}