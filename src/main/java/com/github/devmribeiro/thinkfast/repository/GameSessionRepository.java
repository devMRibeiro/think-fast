package com.github.devmribeiro.thinkfast.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.devmribeiro.thinkfast.model.GameSession;

public interface GameSessionRepository extends CrudRepository<GameSession, Integer> {

	boolean existsByPin(Integer pin);
}