package com.coachassist.backend.parent.service;

import com.coachassist.backend.dto.response.ApiResponse;
import com.coachassist.backend.parent.dto.request.ParentLoginRequest;
import com.coachassist.backend.parent.dto.response.ParentLoginResponse;
import com.coachassist.backend.security.jwt.JwtService;
import com.coachassist.backend.student.entity.Student;
import com.coachassist.backend.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final StudentRepository studentRepository;
    private final JwtService jwtService;

    public Object loginParent(
            ParentLoginRequest request
    ) {

        Optional<Student> optionalStudent =
                studentRepository.findByStudentCode(
                        request.getStudentCode()
                );

        if (optionalStudent.isEmpty()) {

            return new ApiResponse(
                    "Invalid Student Code",
                    false
            );
        }

        Student student =
                optionalStudent.get();

        String token =
                jwtService.generateToken(
                        student.getStudentCode(),
                        "ROLE_PARENT"
                );
        return new ParentLoginResponse(
                token,
                student.getStudentName(),
                student.getParentName(),
                student.getCoaching()
                        .getCoachingName()
        );
    }
}