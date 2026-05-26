package com.siddhant.examportal.dto;

import com.siddhant.examportal.enums.Option;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AnswerRequest {
    private Long questionId;
    private Option selectedOption;
}