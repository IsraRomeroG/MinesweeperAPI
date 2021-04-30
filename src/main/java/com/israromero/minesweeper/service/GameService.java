package com.israromero.minesweeper.service;

import com.israromero.minesweeper.dao.GameDAO;
import com.israromero.minesweeper.dao.UserDAO;
import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import com.israromero.minesweeper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    @Autowired
    GameDAO gameDAO;
    @Autowired
    UserDAO userDAO;

    public Game newGame(int width, int height, int mines){
        Game game = new Game(width, height, mines);
        System.out.println("-- New Game Started --");
        game.getBoard().printBoard();
        gameDAO.save(game);
        return game;
    }

    public Board playGame(UUID id, int x, int y){
        //Faltan validaciones
        Board board = gameDAO.load(id);
        board.reveal(x, y);
        System.out.println("-- I'm playing the game --");
        board.printHiddenBoard();
        gameDAO.save(new Game(id, null, board));
        return board;
    }

    public Board playGame(UUID id, int cellid){
        //Faltan validaciones
        Board board = gameDAO.load(id);
        board.reveal(cellid);
        System.out.println("-- I'm playing the game --");
        board.printHiddenBoard();
        gameDAO.save(new Game(id, null, board));
        return board;
    }

    public boolean saveGame(UUID id, String user){
        //Faltan validaciones
        System.out.println("-- Saving Game --");
        userDAO.saveGame(user, id);
        return true;
    }

    public Board loadUser(UUID id){
        return gameDAO.load(id);
    }

    //New User
    public int newUser(String user){
        System.out.println("En service recibo: "+user);
        User u = new User(user);
        userDAO.save(u);
        return 1;
    }

    public User loadUser(String user){
        return userDAO.load(user);
    }
}
