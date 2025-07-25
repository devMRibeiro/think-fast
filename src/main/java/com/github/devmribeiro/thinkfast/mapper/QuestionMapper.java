package com.github.devmribeiro.thinkfast.mapper;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;
import com.github.devmribeiro.thinkfast.model.Question;
import com.github.devmribeiro.thinkfast.model.Quiz;

public class QuestionMapper {
	public static Question toEntity(QuestionDTO dto, Quiz quiz) {
		Question question = new Question();
		question.setText(dto.text());
		question.setOption(dto.option());
		question.setCorrectOptionIndex(dto.correctOptionIndex());
		question.setQuiz(quiz);
		return question;
	}
}