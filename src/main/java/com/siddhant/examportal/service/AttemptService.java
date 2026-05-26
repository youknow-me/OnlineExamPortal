package com.siddhant.examportal.service;

import com.siddhant.examportal.dto.AnswerRequest;
import com.siddhant.examportal.dto.ResultResponse;
import com.siddhant.examportal.dto.SubmitRequest;
import com.siddhant.examportal.entity.*;
import com.siddhant.examportal.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttemptService {

    private final AttemptRepository attemptRepository;
    private final AnswerRepository answerRepository;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    // Student starts exam
    public Attempt startExam(Long examId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Attempt attempt = Attempt.builder()
                .user(user)
                .exam(exam)
                .startedAt(LocalDateTime.now())
                .build();

        return attemptRepository.save(attempt);
    }

    // Student submits answers → auto graded
    public ResultResponse submitExam(Long attemptId, SubmitRequest request) {
        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        int score = 0;

        // Grade each answer
        for (AnswerRequest answerRequest : request.getAnswers()) {
            Question question = questionRepository.findById(answerRequest.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            boolean isCorrect = question.getCorrectOption()
                    .equals(answerRequest.getSelectedOption());

            Answer answer = Answer.builder()
                    .attempt(attempt)
                    .question(question)
                    .selectedOption(answerRequest.getSelectedOption())
                    .isCorrect(isCorrect)
                    .build();

            answerRepository.save(answer);

            if (isCorrect) {
                score += question.getMarks();
            }
        }

        // Update attempt with score
        attempt.setScore(score);
        attempt.setTotalMarks(attempt.getExam().getTotalMarks());
        attempt.setSubmittedAt(LocalDateTime.now());
        attemptRepository.save(attempt);

        // Build result
        double percentage = ((double) score / attempt.getExam().getTotalMarks()) * 100;

        return ResultResponse.builder()
                .attemptId(attempt.getId())
                .examTitle(attempt.getExam().getTitle())
                .score(score)
                .totalMarks(attempt.getExam().getTotalMarks())
                .percentage(Math.round(percentage * 100.0) / 100.0)
                .passed(percentage >= 50)
                .build();
    }

    // Get result of an attempt
    public ResultResponse getResult(Long attemptId) {
        Attempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        double percentage = ((double) attempt.getScore() / attempt.getTotalMarks()) * 100;

        return ResultResponse.builder()
                .attemptId(attempt.getId())
                .examTitle(attempt.getExam().getTitle())
                .score(attempt.getScore())
                .totalMarks(attempt.getTotalMarks())
                .percentage(Math.round(percentage * 100.0) / 100.0)
                .passed(percentage >= 50)
                .build();
    }
}