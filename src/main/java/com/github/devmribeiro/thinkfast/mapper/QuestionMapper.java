package com.github.devmribeiro.thinkfast.mapper;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;
import com.github.devmribeiro.thinkfast.model.Question;

public class QuestionMapper {
	public static Question toEntity(QuestionDTO dto) {
		Question question = new Question();
		question.setText(dto.text());
		question.setOption(dto.option());
		question.setCorrectOptionIndex(dto.correctOptionIndex());
		return question;
	}
}