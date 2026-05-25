package com.coachassist.backend.attendance.dto.request;

import com.coachassist.backend.attendance.enums.AttendanceStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttendanceRequest {

    private String studentCode;

    private AttendanceStatus status;
}