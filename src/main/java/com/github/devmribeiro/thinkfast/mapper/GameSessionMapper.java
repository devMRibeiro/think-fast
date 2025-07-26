package com.github.devmribeiro.thinkfast.mapper;

import com.github.devmribeiro.thinkfast.dto.gamesession.GameSessionDTO;
import com.github.devmribeiro.thinkfast.enums.GameSessionStatus;
import com.github.devmribeiro.thinkfast.model.GameSession;

public class GameSessionMapper {

	public static GameSessionDTO toDTO(GameSession gameSession, GameSessionStatus status) {
		return new GameSessionDTO(gameSession.getPin(), QuizMapper.toDTO(gameSession.getQuiz()), status);
	}
}