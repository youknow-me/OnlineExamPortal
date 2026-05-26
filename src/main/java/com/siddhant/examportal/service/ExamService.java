package com.siddhant.examportal.service;

import com.siddhant.examportal.dto.ExamRequest;
import com.siddhant.examportal.dto.QuestionRequest;
import com.siddhant.examportal.entity.Exam;
import com.siddhant.examportal.entity.Question;
import com.siddhant.examportal.entity.User;
import com.siddhant.examportal.repository.ExamRepository;
import com.siddhant.examportal.repository.QuestionRepository;
import com.siddhant.examportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    // Admin creates exam
    public Exam createExam(ExamRequest request, String adminEmail) {
        User admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Exam exam = Exam.builder()
                .title(request.getTitle())
                .durationMinutes(request.getDurationMinutes())
                .totalMarks(request.getTotalMarks())
                .isActive(true)
                .createdBy(admin)
                .build();

        return examRepository.save(exam);
    }

    // Admin adds question to exam
    public Question addQuestion(Long examId, QuestionRequest request) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Question question = Question.builder()
                .exam(exam)
                .questionText(request.getQuestionText())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .optionC(request.getOptionC())
                .optionD(request.getOptionD())
                .correctOption(request.getCorrectOption())
                .marks(request.getMarks())
                .build();

        return questionRepository.save(question);
    }

    // Get all active exams
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // Get questions for an exam
    public List<Question> getQuestions(Long examId) {
        return questionRepository.findByExamId(examId);
    }
}