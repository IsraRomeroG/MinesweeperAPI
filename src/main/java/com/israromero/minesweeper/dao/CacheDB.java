package com.israromero.minesweeper.dao;

import com.israromero.minesweeper.model.Board;
import com.israromero.minesweeper.model.Game;
import com.israromero.minesweeper.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Repository
public class CacheDB implements GameDAO, UserDAO{
    HashMap<UUID, Board> DB = new HashMap<UUID, Board>();

    HashMap<String, ArrayList<UUID>> DBusers = new HashMap<String, ArrayList<UUID>>();

    public boolean save(Game game){
        DB.put(game.getId(), game.getBoard());
        //Try catch en caso real, false when can't save
        System.out.println("GAME SAVED");
        return true;
    }

    public Board load(UUID id){
        return DB.get(id);
    }

    @Override
    public boolean save(User user) {
        System.out.println("GONNA MAKE PUT: "+user.getName());
        DBusers.put(user.getName(), user.getGames());
        System.out.println("USER SAVED");
        return true;
    }

    @Override
    public boolean saveGame(String user, UUID idGame) {
        if(DBusers.containsKey(user)){
            ArrayList<UUID> ids = DBusers.get(user);
            ids.add(idGame);
            DBusers.put(user, ids);
            System.out.println("Se agregó: "+idGame+" a "+user);
            return true;
        }else{
            System.out.println("No lo encontró ...");
            return false;
        }
    }

    @Override
    public User load(String user) {
        System.out.println("LOADING USER ... "+user);
        if(DBusers.containsKey(user)){
            System.out.println("Si lo encontró ...");
            return new User(user, DBusers.get(user));
        }else{
            System.out.println("No lo encontró ...");
            return new User(null, null);
        }
    }
}
