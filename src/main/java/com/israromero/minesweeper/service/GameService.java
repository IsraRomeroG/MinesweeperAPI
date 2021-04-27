package com.israromero.minesweeper.service;

import com.israromero.minesweeper.dao.GameDAO;
import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    @Autowired
    GameDAO gameDAO;

    public Game newGame(int width, int height, int mines){
        Game game = new Game(width, height, mines);
        gameDAO.save(game);
        return game;
    }

    public Board playGame(UUID id, int x, int y){
        //Faltan validaciones
        Board board = gameDAO.load(id);
        board.reveal(x, y);
        System.out.println("-- I'm playing the game --");
        board.printHiddenBoard();
        gameDAO.save(new Game(id, board));
        return board;
    }



    public Board loadGame(UUID id){
        return gameDAO.load(id);
    }
}
