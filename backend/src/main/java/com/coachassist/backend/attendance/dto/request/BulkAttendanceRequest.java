package com.coachassist.backend.attendance.dto.request;


import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BulkAttendanceRequest {

    List<AttendanceRequest> attendanceList;
}
