package com.siddhant.examportal.repository;

import com.siddhant.examportal.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

List<Attempt> findByExamId(Long examId);
List<Attempt> findByUserId(Long userId);

}
