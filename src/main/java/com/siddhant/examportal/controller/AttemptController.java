package com.siddhant.examportal.controller;

import com.siddhant.examportal.dto.ResultResponse;
import com.siddhant.examportal.dto.SubmitRequest;
import com.siddhant.examportal.entity.Attempt;
import com.siddhant.examportal.service.AttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attempts")
@RequiredArgsConstructor
public class AttemptController {

    private final AttemptService attemptService;

    @PostMapping("/start/{examId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<Attempt> startExam(@PathVariable Long examId,
                                             Authentication authentication) {
        return ResponseEntity.ok(attemptService.startExam(examId, authentication.getName()));
    }

    @PostMapping("/submit/{attemptId}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ResultResponse> submitExam(@PathVariable Long attemptId,
                                                     @RequestBody SubmitRequest request) {
        return ResponseEntity.ok(attemptService.submitExam(attemptId, request));
    }

    @GetMapping("/result/{attemptId}")
    public ResponseEntity<ResultResponse> getResult(@PathVariable Long attemptId) {
        return ResponseEntity.ok(attemptService.getResult(attemptId));
    }
}