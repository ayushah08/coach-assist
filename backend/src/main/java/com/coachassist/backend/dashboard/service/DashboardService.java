package com.coachassist.backend.dashboard.service;

import com.coachassist.backend.attendance.entity.Attendance;
import com.coachassist.backend.attendance.enums.AttendanceStatus;
import com.coachassist.backend.attendance.repository.AttendanceRepository;
import com.coachassist.backend.dashboard.dto.response.DashboardSummaryResponse;
import com.coachassist.backend.marks.entity.Marks;
import com.coachassist.backend.marks.repository.MarksRepository;
import com.coachassist.backend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;

    public DashboardSummaryResponse getDashboardSummary() {

        long totalStudents =
                studentRepository.count();

        long presentToday =
                attendanceRepository
                        .countByAttendanceDateAndStatus(
                                LocalDate.now(),
                                AttendanceStatus.PRESENT
                        );

        long absentToday =
                attendanceRepository
                        .countByAttendanceDateAndStatus(
                                LocalDate.now(),
                                AttendanceStatus.ABSENT
                        );

        List<Marks> marksList =
                marksRepository.findAll();

        double totalPercentage = 0;

        for (Marks marks : marksList) {

            double percentage =
                    (marks.getMarksObtained()
                            / marks.getTotalMarks()) * 100;

            totalPercentage += percentage;
        }

        double averagePerformance = 0;

        if (!marksList.isEmpty()) {

            averagePerformance =
                    totalPercentage / marksList.size();
        }

        return new DashboardSummaryResponse(
                totalStudents,
                presentToday,
                absentToday,
                averagePerformance
        );
    }
}