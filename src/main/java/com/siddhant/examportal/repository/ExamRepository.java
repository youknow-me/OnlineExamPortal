package com.siddhant.examportal.repository;

import com.siddhant.examportal.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamRepository extends JpaRepository<Exam, Long> {}
