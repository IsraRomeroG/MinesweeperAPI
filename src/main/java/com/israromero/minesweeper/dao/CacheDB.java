package com.israromero.minesweeper.dao;

import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.UUID;

@Repository
public class CacheDB implements GameDAO{
    HashMap<UUID, Board> DB = new HashMap<UUID, Board>();

    public boolean save(Game game){
        DB.put(game.getId(), game.getBoard());
        //Try catch en caso real, false when can't save
        System.out.println("GAME SAVED");
        return true;
    }

    public Board load(UUID id){
        return DB.get(id);
    }
}
