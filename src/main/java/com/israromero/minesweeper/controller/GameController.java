package com.israromero.minesweeper.controller;

import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import com.israromero.minesweeper.model.User;
import com.israromero.minesweeper.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://israromero.com")
@RequestMapping("/minesweeper/v1/")
public class GameController {

    @Autowired
    GameService gameService;

    //New Game
    @GetMapping("{width}/{height}/{mines}")
    @ApiOperation("Create a New Game with the specified parameters")
    @ApiResponse(code= 200, message = "Ok")
    public Game newGame(
            @ApiParam(value="The width of the board game") @PathVariable("width") int width,
            @ApiParam(value="The height of the board game") @PathVariable("height") int height,
            @ApiParam(value="The number of mines of the board game") @PathVariable("mines") int mines){
        return gameService.newGame(width, height, mines);
    }

    /* Not Used
    //Play Game
    @PutMapping("{id}/{x}/{y}")
    public Board playGame(@PathVariable("id") UUID id, @PathVariable("x") int x, @PathVariable("y") int y){
        return gameService.playGame(id, x, y);
    }*/

    @GetMapping("{gameId}/{cellId}")
    @ApiOperation("Open 1 cell from the board and get the updated board")
    public Board playGame(
            @ApiParam(value="The id of the game", example = "731e1fce-3143-4bec-a79c-8b9a0b0804cd") @PathVariable("gameId") UUID id,
            @ApiParam(value="The id of the cell played") @PathVariable("cellId") int cellId){
        return gameService.playGame(id, cellId);
    }

    //Save Game
    @PostMapping("{gameId}/{user}")
    @ApiOperation("Save a game in progress")
    public boolean saveGame(
            @ApiParam(value="The id of the game", example = "731e1fce-3143-4bec-a79c-8b9a0b0804cd") @PathVariable("gameId") UUID id,
            @ApiParam(value="The user") @PathVariable("user") String user){
        return gameService.saveGame(id, user);
    }

    //Load Game
    @GetMapping("{gameId}")
    @ApiOperation("Load a saved game")
    public Board loadGame(@ApiParam(value="The id of the game", example = "731e1fce-3143-4bec-a79c-8b9a0b0804cd") @PathVariable("gameId") UUID id){
        return gameService.loadUser(id);
    }

    @PostMapping("users/{user}")
    @ApiOperation("Save a new user")
    public int newUser(@ApiParam(value="The user") @PathVariable("user") String user, @ApiParam(value="All the game ids of the user")  ArrayList<String> games){
        gameService.newUser(user);
        return 1;
    }

    @GetMapping("users/{user}")
    @ApiOperation("Load a saved user")
    public User loadUser(@ApiParam(value="The user to load")  @PathVariable("user") String user){
        return gameService.loadUser(user);
    }
}
