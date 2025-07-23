package com.github.devmribeiro.thinkfast.dto.question;

import java.util.List;

public record QuestionDTO (String text, List<String> option, int correctOptionIndex) { }