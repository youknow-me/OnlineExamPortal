package com.siddhant.examportal.dto;


import com.siddhant.examportal.enums.Option;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor

public class QuestionRequest {
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Option correctOption;
    private int marks;
}
