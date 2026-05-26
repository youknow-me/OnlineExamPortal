package com.siddhant.examportal.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ResultResponse {
    private Long attemptId;
    private String examTitle;
    private int score;
    private int totalMarks;
    private double percentage;
    private boolean passed;
}