package com.github.devmribeiro.thinkfast.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;
import com.github.devmribeiro.thinkfast.dto.quiz.QuizDTO;
import com.github.devmribeiro.thinkfast.model.Question;
import com.github.devmribeiro.thinkfast.model.Quiz;

public class QuizMapper {
	public static Quiz toEntity(QuizDTO dto) {
	    Quiz quiz = new Quiz();
	    quiz.setTitle(dto.title());

	    List<Question> questions = questionsToEntities(dto.questionDTOList(), quiz);

	    for (Question q : questions)
	    	q.setQuiz(quiz);

	    quiz.setQuestions(questions);

	    return quiz;
	}

	public static List<Question> questionsToEntities(List<QuestionDTO> questionDTOList, Quiz quiz) {
		if (questionDTOList == null)
			return new ArrayList<Question>();

		List<Question> questionList = new ArrayList<Question>(questionDTOList.size());

		for (QuestionDTO dto : questionDTOList)
			questionList.add(QuestionMapper.toEntity(dto, quiz));

		return questionList;
	}

	public static QuizDTO toDTO(Quiz quiz) {
		if (quiz != null) {
			List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>(quiz.getQuestions().size());

			for (Question question : quiz.getQuestions()) {
				questionDTOList.add(
						new QuestionDTO(
								question.getText(),
								question.getOption(),
								question.getCorrectOptionIndex())
				);
			}
			return new QuizDTO(quiz.getTitle(), questionDTOList);
		}
		return null;
	}
}