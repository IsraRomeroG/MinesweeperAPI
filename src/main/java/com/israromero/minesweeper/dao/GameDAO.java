package com.israromero.minesweeper.dao;

import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;

import java.util.UUID;

public interface GameDAO {
    public boolean save(Game game);
    public Board load(UUID id);
}
