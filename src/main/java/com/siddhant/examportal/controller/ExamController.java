package com.siddhant.examportal.controller;

import com.siddhant.examportal.dto.ExamRequest;
import com.siddhant.examportal.dto.QuestionRequest;
import com.siddhant.examportal.entity.Exam;
import com.siddhant.examportal.entity.Question;
import com.siddhant.examportal.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Exam> createExam(@RequestBody ExamRequest request,
                                           Authentication authentication) {
        return ResponseEntity.ok(examService.createExam(request, authentication.getName()));
    }

    @PostMapping("/{examId}/questions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Question> addQuestion(@PathVariable Long examId,
                                                @RequestBody QuestionRequest request) {
        return ResponseEntity.ok(examService.addQuestion(examId, request));
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @GetMapping("/{examId}/questions")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long examId) {
        return ResponseEntity.ok(examService.getQuestions(examId));
    }
}