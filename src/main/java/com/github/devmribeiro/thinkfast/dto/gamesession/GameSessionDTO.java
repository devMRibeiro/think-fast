package com.github.devmribeiro.thinkfast.dto.gamesession;

import com.github.devmribeiro.thinkfast.dto.quiz.QuizDTO;
import com.github.devmribeiro.thinkfast.enums.GameSessionStatus;

public record GameSessionDTO (Integer pin, QuizDTO quiz, GameSessionStatus status) { }