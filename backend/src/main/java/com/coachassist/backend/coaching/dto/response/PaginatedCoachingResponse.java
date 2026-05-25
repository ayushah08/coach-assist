package com.coachassist.backend.coaching.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PaginatedCoachingResponse {

    private List<CoachingResponse> coachings;

    private int currentPage;

    private int pageSize;

    private int totalPages;

    private long totalCoachings;
}