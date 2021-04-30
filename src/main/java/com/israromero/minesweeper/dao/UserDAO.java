package com.israromero.minesweeper.dao;

import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import com.israromero.minesweeper.model.User;

import java.util.UUID;

public interface UserDAO {

    public boolean save(User user);
    public boolean saveGame(String user, UUID game);
    public User load(String user);
}
