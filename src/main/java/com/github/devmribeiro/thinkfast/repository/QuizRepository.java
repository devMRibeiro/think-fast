package com.github.devmribeiro.thinkfast.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.devmribeiro.thinkfast.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	Quiz findQuizById(Long quizId);
}