package com.github.devmribeiro.thinkfast.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.devmribeiro.thinkfast.dto.quiz.QuizDTO;
import com.github.devmribeiro.thinkfast.service.QuizService;
import com.github.devmribeiro.thinkfast.util.ApiResponse;

@RestController
@RequestMapping(path = "/quizzes")
public class QuizController {

	private final QuizService quizService;
	
	public QuizController(QuizService quizService) {
		this.quizService = quizService;
	}

	@ResponseBody
	@GetMapping("/{quizId}")
	public ResponseEntity<QuizDTO> listById(@PathVariable("quizId") Long quizId) {
		return ResponseEntity.ok(quizService.listById(quizId));
	}

	@PostMapping
	public ResponseEntity<ApiResponse> create(@RequestBody QuizDTO quizDTO) {
	    Long id = quizService.save(quizDTO);
	    return ResponseEntity.created(URI.create("/quizzes/" + id))
	        .body(new ApiResponse("Successfully Added Quiz", id));
	}

	@PutMapping("/{quizId}")
	public ResponseEntity<ApiResponse> edit(@PathVariable Long quizId, @RequestBody QuizDTO quizDTO) {
		return ResponseEntity.ok(new ApiResponse("Successfully Updated Quiz", quizService.edit(quizId, quizDTO)));
	}
}