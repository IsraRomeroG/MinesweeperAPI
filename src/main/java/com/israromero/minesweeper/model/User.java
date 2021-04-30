package com.israromero.minesweeper.model;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String name;
    private ArrayList<UUID> games;

    public User(String user) {
        this.name = user;
        this.games = new ArrayList<UUID>();
    }

    public User(String name, ArrayList<UUID> games) {
        this.name = name;
        this.games = games;
    }

    public ArrayList<UUID> getGames() {
        return games;
    }

    public void setGames(ArrayList<UUID> games) {
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
