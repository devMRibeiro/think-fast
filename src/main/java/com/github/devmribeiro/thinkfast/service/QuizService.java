package com.github.devmribeiro.thinkfast.service;

import org.springframework.stereotype.Service;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;
import com.github.devmribeiro.thinkfast.dto.quiz.QuizDTO;
import com.github.devmribeiro.thinkfast.exception.BadRequestException;
import com.github.devmribeiro.thinkfast.exception.ResourceNotFoundException;
import com.github.devmribeiro.thinkfast.mapper.QuizMapper;
import com.github.devmribeiro.thinkfast.model.Quiz;
import com.github.devmribeiro.thinkfast.repository.QuizRepository;

@Service
public class QuizService {
	private final QuizRepository quizRepository;

	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}

	public Quiz findById(Long quizId) {
		return quizRepository.findQuizByQuizId(quizId);
	}

	public QuizDTO listById(Long quizId) {

		Quiz quiz = quizRepository.findQuizByQuizId(quizId);

		if (quiz == null)
	        throw new ResourceNotFoundException("Quiz not found");

		return QuizMapper.toDTO(quiz);
	}

	public Long save(QuizDTO quizDTO) {

		validateQuizDTO(quizDTO);

		return quizRepository.save(QuizMapper.toEntity(quizDTO)).getQuizId();
	}

	public Long edit(Long quizId, QuizDTO quizDTO) {

		Quiz quiz = quizRepository.findQuizByQuizId(quizId);

		if (quiz == null)
			throw new ResourceNotFoundException("Quiz not found");

		validateQuizDTO(quizDTO);

		quiz.setTitle(quizDTO.title());
		quiz.setQuestions(QuizMapper.questionsToEntities(quizDTO.questionDTOList(), quiz));

		return quizRepository.save(quiz).getQuizId();
	}
	
	private void validateQuizDTO(QuizDTO quizDTO) {
		if (quizDTO.title() == null || quizDTO.title().isBlank())
			throw new BadRequestException("Quiz must contain one Title");
		
		if (quizDTO.questionDTOList() == null || quizDTO.questionDTOList().size() > 10)
			throw new BadRequestException("Quiz must contain at least one question and a maximum of 10");
		
		for (QuestionDTO dto : quizDTO.questionDTOList()) {
			if (dto.text() == null || dto.text().isBlank())
				throw new BadRequestException("Each question must contain a statement");
			
			if (dto.option() == null || dto.option().size() != 4)
				throw new BadRequestException("Each question must have 4 options");
			
			if (dto.correctOptionIndex() < 0 || dto.correctOptionIndex() > 3)
				throw new BadRequestException("Each question must have one correct option");
		}
	}
}