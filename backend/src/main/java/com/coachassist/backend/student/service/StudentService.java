package com.coachassist.backend.student.service;

import com.coachassist.backend.coaching.entity.Coaching;
import com.coachassist.backend.coaching.repository.CoachingRepository;
import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.student.dto.request.StudentCreateRequest;
import com.coachassist.backend.student.dto.request.UpdateStudentRequest;
import com.coachassist.backend.student.dto.response.PaginatedStudentResponse;
import com.coachassist.backend.student.dto.response.StudentDetailsResponse;
import com.coachassist.backend.student.dto.response.StudentResponse;
import com.coachassist.backend.student.entity.Student;
import com.coachassist.backend.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CoachingRepository coachingRepository;

    public Object createStudent(StudentCreateRequest
                                     request){

        Optional<Coaching> optionalCoaching = coachingRepository.findByCoachingCode(request.getCoachingCode());

        if(optionalCoaching.isEmpty()){
            return new ApiResponse("Coaching Not Found  " , false);
        }


        Coaching coaching =
                optionalCoaching.get();

        Student student = new Student();

        student.setStudentName(
                request.getStudentName()
        );

        student.setClassName(
                request.getClassName()
        );

        student.setParentName(
                request.getParentName()
        );

        student.setParentPhone(
                request.getParentPhone()
        );

        student.setStudentCode(
                generateStudentCode()
        );

        student.setCoaching(coaching);

        studentRepository.save(student);

        return new StudentResponse(
                student.getStudentName(),
                student.getStudentCode(),
                student.getClassName(),
                student.getParentName()

        );
        

    }

    private String generateStudentCode() {

        Random random = new Random();

        int number =
                1000 + random.nextInt(9000);

        return "STU-" + number;
    }

    public PaginatedStudentResponse getAllStudents(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Student> studentPage =
                studentRepository.findAll(pageable);

        List<StudentResponse> students =
                studentPage.getContent()
                        .stream()
                        .map(student ->
                                new StudentResponse(
                                        student.getStudentName(),
                                        student.getStudentCode(),
                                        student.getClassName(),
                                        student.getParentName()
                                )
                        )
                        .toList();

        return new PaginatedStudentResponse(
                students,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements()
        );
    }
    public Object getStudentDetails(
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

        return new StudentDetailsResponse(
                student.getStudentName(),
                student.getStudentCode(),
                student.getClassName(),
                student.getParentName(),
                student.getParentPhone(),
                student.getCoaching()
                        .getCoachingName()
        );
    }

    public ApiResponse updateStudent(

            String studentCode,

            UpdateStudentRequest request
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

        student.setStudentName(
                request.getStudentName()
        );

        student.setClassName(
                request.getClassName()
        );

        student.setParentName(
                request.getParentName()
        );

        student.setParentPhone(
                request.getParentPhone()
        );

        studentRepository.save(student);

        return new ApiResponse(
                "Student Updated Successfully",
                true
        );
    }

    public ApiResponse deleteStudent(
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

        studentRepository.delete(student);

        return new ApiResponse(
                "Student Deleted Successfully",
                true
        );
    }

    public PaginatedStudentResponse searchStudents(

            String keyword,

            int page,

            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Student> studentPage =
                studentRepository
                        .findByStudentNameContainingIgnoreCase(
                                keyword,
                                pageable
                        );

        List<StudentResponse> students =
                studentPage.getContent()
                        .stream()
                        .map(student ->
                                new StudentResponse(
                                        student.getStudentName(),
                                        student.getStudentCode(),
                                        student.getClassName(),
                                        student.getParentName()
                                )
                        )
                        .toList();

        return new PaginatedStudentResponse(
                students,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements()
        );
    }
}
