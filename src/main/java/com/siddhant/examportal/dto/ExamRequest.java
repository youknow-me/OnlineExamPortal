package com.siddhant.examportal.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class ExamRequest {
    private String title;
    private int durationMinutes;
    private int totalMarks;
}
