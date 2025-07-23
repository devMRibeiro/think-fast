package com.github.devmribeiro.thinkfast.dto.question;

import java.util.List;

import com.github.devmribeiro.thinkfast.model.Question;
import com.github.devmribeiro.thinkfast.model.Quiz;

public class QuestionDTO {
	private String text;
	private List<String> option;
	private int correctOptionIndex;
	private Quiz quiz;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getOption() {
		return option;
	}
	public void setOption(List<String> option) {
		this.option = option;
	}
	public int getCorrectOptionIndex() {
		return correctOptionIndex;
	}
	public void setCorrectOptionIndex(int correctOptionIndex) {
		this.correctOptionIndex = correctOptionIndex;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public Question toEntity() {
		Question question = new Question();
		question.setText(this.text);
		question.setOption(this.option);
		question.setCorrectOptionIndex(this.correctOptionIndex);
		return question;
	}
}