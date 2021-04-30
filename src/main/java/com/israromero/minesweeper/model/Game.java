package com.israromero.minesweeper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Game {
    @JsonProperty
    private final UUID id;
    @JsonProperty
    private String user;
    @JsonProperty
    private Board board;

    public Game(UUID id, String user, Board board){
        this.id = id;
        this.user = user;
        this.board = board;
    }

    public Game(int width, int height, int mines){
        id = UUID.randomUUID();
        board = new Board(width, height, mines);
    }

    public UUID getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
