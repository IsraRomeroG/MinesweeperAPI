package com.israromero.minesweeper.controller;

import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import com.israromero.minesweeper.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/minesweeper/v1/")
public class GameController {

    @Autowired
    GameService gameService;

    //New Game
    @GetMapping("{width}/{height}/{mines}")
    public Game newGame(@PathVariable("width") int width, @PathVariable("height") int height, @PathVariable("mines") int mines){
        return gameService.newGame(width, height, mines);
    }

    //Play Game
    @PutMapping("{id}/{x}/{y}")
    public Board playGame(@PathVariable("id") UUID id, @PathVariable("x") int x, @PathVariable("y") int y){
        return gameService.playGame(id, x, y);
    }

    @GetMapping("{id}/{cellId}")
    public Board playGame(@PathVariable("id") UUID id, @PathVariable("cellId") int cellId){
        return gameService.playGame(id, cellId);
    }

    //Save Game

    //Load Game
    @GetMapping("{id}")
    public Board loadGame(@PathVariable("id") UUID id){
        return gameService.loadGame(id);
    }
}
