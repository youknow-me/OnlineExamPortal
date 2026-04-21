package com.siddhant.examportal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int durationMinutes;

    @Column(nullable = false)
    private int totalMarks;

    @Column(nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    private LocalDateTime createdAt;

    @PrePersist
    protected  void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
