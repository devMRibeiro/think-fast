package com.github.devmribeiro.thinkfast.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.github.devmribeiro.thinkfast.dto.gamesession.GameSessionDTO;
import com.github.devmribeiro.thinkfast.enums.GameSessionStatus;
import com.github.devmribeiro.thinkfast.exception.ResourceNotFoundException;
import com.github.devmribeiro.thinkfast.mapper.GameSessionMapper;
import com.github.devmribeiro.thinkfast.model.GameSession;
import com.github.devmribeiro.thinkfast.model.Quiz;
import com.github.devmribeiro.thinkfast.repository.GameSessionRepository;
import com.github.devmribeiro.thinkfast.repository.QuizRepository;

@Service
public class GameSessionService {

	private final GameSessionRepository gameSessionRepository;
	private final QuizRepository quizRepository;

	public GameSessionService(QuizRepository quizRepository, GameSessionRepository gameSessionRepository) {
		this.quizRepository = quizRepository;
		this.gameSessionRepository = gameSessionRepository;
	}

	public GameSessionDTO createSession(Long quizId) {
	    Quiz quiz = quizRepository.findQuizByQuizId(quizId);

	    if (quiz == null)
	        new ResourceNotFoundException("Quiz not found");

	    int pin;
	    do {
	        pin = generatePin();
	    } while (gameSessionRepository.existsByPin(pin));

	    GameSession session = new GameSession();
	    session.setPin(pin);
	    session.setQuiz(quiz);

	    return GameSessionMapper.toDTO(gameSessionRepository.save(session), GameSessionStatus.WAITING);
	}

	private int generatePin() {
	    return 100000 + new Random().nextInt(900000);
	}
}