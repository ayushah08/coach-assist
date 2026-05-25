package com.coachassist.backend.attendance.service;

import com.coachassist.backend.attendance.dto.request.AttendanceRequest;
import com.coachassist.backend.attendance.dto.request.BulkAttendanceRequest;
import com.coachassist.backend.attendance.dto.response.AttendanceAnalyticsResponse;
import com.coachassist.backend.attendance.dto.response.AttendanceHistoryResponse;
import com.coachassist.backend.attendance.entity.Attendance;
import com.coachassist.backend.attendance.enums.AttendanceStatus;
import com.coachassist.backend.attendance.repository.AttendanceRepository;
import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.student.entity.Student;
import com.coachassist.backend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public ApiResponse markAttendance(
            AttendanceRequest request
    ) {

        Optional<Student> optionalStudent =
                studentRepository.findByStudentCode(
                        request.getStudentCode()
                );

        if (optionalStudent.isEmpty()) {

            return new ApiResponse(
                    "Student Not Found",
                    false
            );
        }

        Student student =
                optionalStudent.get();

        Attendance attendance =
                new Attendance();

        attendance.setAttendanceDate(
                LocalDate.now()
        );

        attendance.setStatus(
                request.getStatus()
        );

        attendance.setStudent(student);

        attendanceRepository.save(attendance);

        return new ApiResponse(
                "Attendance Marked Successfully",
                true
        );
    }

    public ApiResponse bulkAttendance(
            BulkAttendanceRequest bulkAttendanceRequest
    ) {
        for (AttendanceRequest attendanceRequest : bulkAttendanceRequest.getAttendanceList()) {

            Optional<Student> optionalStudent = studentRepository.findByStudentCode(attendanceRequest.getStudentCode());

            if (optionalStudent.isEmpty()) {
                continue;
            }

            Student student =
                    optionalStudent.get();

            Attendance attendance =  new Attendance();

            attendance.setAttendanceDate(LocalDate.now());

            attendance.setStatus(attendanceRequest.getStatus());

            attendance.setStudent(student);

            attendanceRepository.save(attendance);

        }


        return new ApiResponse(
                "Bulk Attendance Marked Successfully",
                true
        );


    }

    public Object getAttendanceAnalytics(
            String studentCode
    ) {

        Optional<Student> optionalStudent =
                studentRepository.findByStudentCode(
                        studentCode
                );

        if (optionalStudent.isEmpty()) {

            return new ApiResponse(
                    "Student Not Found",
                    false
            );
        }

        Student student =
                optionalStudent.get();

        long presentDays =
                attendanceRepository.countByStudentAndStatus(
                        student,
                        AttendanceStatus.PRESENT
                );

        long absentDays =
                attendanceRepository.countByStudentAndStatus(
                        student,
                        AttendanceStatus.ABSENT
                );

        long totalDays =
                presentDays + absentDays;

        double percentage = 0;

        if (totalDays > 0) {

            percentage =
                    ((double) presentDays / totalDays) * 100;
        }

        return new AttendanceAnalyticsResponse(
                student.getStudentName(),
                presentDays,
                absentDays,
                percentage
        );
    }
    public Object getStudentAttendanceHistory(
            String studentCode
    ) {

        Optional<Student> optionalStudent =
                studentRepository.findByStudentCode(
                        studentCode
                );

        if (optionalStudent.isEmpty()) {

            return new ApiResponse(
                    "Student Not Found",
                    false
            );
        }

        Student student =
                optionalStudent.get();

        List<Attendance> attendanceList =
                attendanceRepository.findByStudent(student);

        return attendanceList.stream()
                .map(attendance ->
                        new AttendanceHistoryResponse(
                                attendance.getAttendanceDate()
                                        .toString(),
                                attendance.getStatus()
                                        .toString()
                        )
                )
                .toList();
    }
}