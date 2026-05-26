package com.siddhant.examportal.repository;


import com.siddhant.examportal.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExamId(Long examId);
}
