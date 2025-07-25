package com.github.devmribeiro.thinkfast.exception;

import java.time.LocalDateTime;

public record ErrorMessage ( 
	int statusCode,
	LocalDateTime timestamp,
	String message,
	String description) { }