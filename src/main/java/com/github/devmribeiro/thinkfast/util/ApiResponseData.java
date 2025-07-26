package com.github.devmribeiro.thinkfast.util;

public record ApiResponseData<E> (String message, E data) { }