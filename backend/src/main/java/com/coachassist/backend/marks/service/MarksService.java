package com.coachassist.backend.marks.service;

import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.marks.dto.request.MarksRequest;
import com.coachassist.backend.marks.dto.response.MarksResponse;
import com.coachassist.backend.marks.dto.response.PerformanceAnalyticsResponse;
import com.coachassist.backend.marks.entity.Marks;
import com.coachassist.backend.marks.repository.MarksRepository;
import com.coachassist.backend.student.entity.Student;
import com.coachassist.backend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;

    public ApiResponse addStudentMarks(
            MarksRequest request
    ) {

        Optional<Student> optionalStudent = studentRepository.findByStudentCode(request.getStudentCode());

        if (optionalStudent.isEmpty()) {
            return
                    new ApiResponse(" Student Not Found ", false);
        }

        Student student = optionalStudent.get();

        Marks marks = new Marks();

        marks.setStudent(student);
        marks.setExamName(request.getExamName());
        marks.setSubject(request.getSubject());
        marks.setTotalMarks(request.getTotalMarks());
        marks.setMarksObtained(request.getMarksObtained());

        marksRepository.save(marks);

        return new ApiResponse(" Marks Added Succesfully for : " + student.getStudentName(), true);
    }

    public Object getPerformanceAnalytics(
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

        List<Marks> marksList =
                marksRepository.findByStudent(student);

        if (marksList.isEmpty()) {

            return new ApiResponse(
                    "No Marks Found",
                    false
            );
        }

        double totalPercentage = 0;

        for (Marks marks : marksList) {

            double percentage =
                    (marks.getMarksObtained()
                            / marks.getTotalMarks()) * 100;

            totalPercentage += percentage;
        }

        double averagePercentage =
                totalPercentage / marksList.size();

        return new PerformanceAnalyticsResponse(
                student.getStudentName(),
                averagePercentage,
                marksList.size()
        );
    }

    public Object getStudentMarks(
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

        List<Marks> marksList =
                marksRepository.findByStudent(student);

        return marksList.stream()
                .map(marks -> {

                    double percentage =
                            (marks.getMarksObtained()
                                    / marks.getTotalMarks()) * 100;

                    return new MarksResponse(
                            marks.getSubject(),
                            marks.getExamName(),
                            marks.getMarksObtained(),
                            marks.getTotalMarks(),
                            percentage
                    );
                })
                .toList();
    }
}
