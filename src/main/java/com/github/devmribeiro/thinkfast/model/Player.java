package com.github.devmribeiro.thinkfast.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Player {

	@Id
	@SequenceGenerator(name = "player_id_seq", sequenceName = "player_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq")
	private Long playerId;

	private String name;

	private int score;

	@ManyToOne
	@JoinColumn(name = "session_id")
	private GameSession session;

	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public GameSession getSession() {
		return session;
	}
	public void setSession(GameSession session) {
		this.session = session;
	}
}