package com.github.devmribeiro.thinkfast.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.devmribeiro.thinkfast.dto.gamesession.GameSessionDTO;
import com.github.devmribeiro.thinkfast.service.GameSessionService;
import com.github.devmribeiro.thinkfast.util.ApiResponseData;

@RestController
@RequestMapping(path = "/v1/sessions")
public class GameSessionController {

	private final GameSessionService gameSessionService;
	
	public GameSessionController(GameSessionService gameSessionService) {
		this.gameSessionService = gameSessionService;
	}

	@PostMapping
	public ResponseEntity<ApiResponseData<GameSessionDTO>> createSession(@RequestParam Long quizId) {
		GameSessionDTO dto = gameSessionService.createSession(quizId);
	    return ResponseEntity
	        .created(URI.create("/sessions/" + dto.pin()))
	        .body(new ApiResponseData<GameSessionDTO>("Session created successfully", dto));
	}
}