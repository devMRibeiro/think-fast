package com.github.devmribeiro.thinkfast.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;
import com.github.devmribeiro.thinkfast.dto.quiz.QuizDTO;
import com.github.devmribeiro.thinkfast.mapper.QuizMapper;
import com.github.devmribeiro.thinkfast.model.Quiz;
import com.github.devmribeiro.thinkfast.repository.QuizRepository;

@Service
public class QuizService {
	private final QuizRepository quizRepository;

	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}
	
	public QuizDTO listById(Long quizId) {

		Quiz quiz = quizRepository.findQuizByQuizId(quizId);

		if (quiz == null)
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");

		return QuizMapper.toDTO(quiz);
	}

	public Long save(QuizDTO quizDTO) {

		if (quizDTO.title() == null || quizDTO.title().isBlank())
			throw new IllegalArgumentException("Quiz must contain one Title");

		if (quizDTO.questionDTOList() == null || quizDTO.questionDTOList().size() > 10)
            throw new IllegalArgumentException("Quiz must contain at least one question and a maximum of 10");

		for (QuestionDTO dto : quizDTO.questionDTOList()) {
			if (dto.text() == null || dto.text().isBlank())
                throw new IllegalArgumentException("Each question must contain a statement");

			if (dto.option() == null || dto.option().size() != 4)
                throw new IllegalArgumentException("Each question must have 4 options");

			if (dto.correctOptionIndex() < 0 || dto.correctOptionIndex() > 3)
                throw new IllegalArgumentException("Each question must have one correct option");
		}

		return quizRepository.save(QuizMapper.toEntity(quizDTO)).getQuizId();
	}
}