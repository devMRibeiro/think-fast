package com.github.devmribeiro.thinkfast.dto.quiz;

import java.util.List;

import com.github.devmribeiro.thinkfast.dto.question.QuestionDTO;

public record QuizDTO (String title, List<QuestionDTO> questionDTOList) { }