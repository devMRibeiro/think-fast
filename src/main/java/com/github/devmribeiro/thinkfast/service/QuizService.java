package com.github.devmribeiro.thinkfast.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;
import com.github.devmribeiro.thinkfast.dto.quiz.QuizDTO;
import com.github.devmribeiro.thinkfast.model.Question;
import com.github.devmribeiro.thinkfast.model.Quiz;
import com.github.devmribeiro.thinkfast.repository.QuizRepository;

@Service
public class QuizService {
	private final QuizRepository quizRepository;

	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}
	
	public QuizDTO listById(Long quizId) {

		Quiz quiz = quizRepository.findQuizById(quizId);

		if (quiz == null)
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");

		return toDTO(quiz);
	}

	public Long save(QuizDTO quizDTO) {

		if (quizDTO.title() == null || quizDTO.title().isBlank())
			throw new IllegalArgumentException("Quiz must contain one Title");

		if (quizDTO.questionDTOList() == null || quizDTO.questionDTOList().size() > 10)
            throw new IllegalArgumentException("Quiz must contain at least one question and a maximum of 10");

		for (QuestionDTO dto : quizDTO.questionDTOList()) {
			if (dto.getText() == null || dto.getText().isBlank())
                throw new IllegalArgumentException("Each question must contain a statement");

			if (dto.getOption() == null || dto.getOption().size() != 4)
                throw new IllegalArgumentException("Each question must have 4 options");

			if (dto.getCorrectOptionIndex() != 1)
                throw new IllegalArgumentException("Each question must have one correct option");
		}

		return quizRepository.save(toEntity(quizDTO)).getQuizId();
	}
	
	private QuizDTO toDTO(Quiz quiz) {
		if (quiz != null) {
			List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>(quiz.getQuestions().size());

			for (Question question : quiz.getQuestions()) {
				QuestionDTO dto = new QuestionDTO();
				dto.setText(question.getText());
				dto.setOption(question.getOption());
				dto.setCorrectOptionIndex(question.getCorrectOptionIndex());
				questionDTOList.add(dto);
			}
			return new QuizDTO(quiz.getTitle(), questionDTOList);
		}
		return null;
	}
	
	private Quiz toEntity(QuizDTO dto) {
	    Quiz quiz = new Quiz();
	    quiz.setTitle(dto.title());

	    List<Question> questions = questionsToEntities(dto.questionDTOList());

	    for (Question q : questions)
	    	q.setQuiz(quiz);

	    quiz.setQuestions(questions);

	    return quiz;
	}
	
	private List<Question> questionsToEntities(List<QuestionDTO> questionDTOList) {
		if (questionDTOList == null)
			return new ArrayList<Question>();
	
		List<Question> questionList = new ArrayList<Question>(questionDTOList.size());
	
		for (QuestionDTO dto : questionDTOList)
			questionList.add(dto.toEntity());
	
		return questionList;
	}
}