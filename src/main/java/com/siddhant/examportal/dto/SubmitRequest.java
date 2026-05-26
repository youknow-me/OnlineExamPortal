package com.siddhant.examportal.dto;

import lombok.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SubmitRequest {
    private List<AnswerRequest> answers;
}