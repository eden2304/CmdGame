package com.company;
import com.company.Enemies.Enemy;
import com.company.Players.Player;
import com.company.Tiles.Tile;

import java.util.*;

public class GameController {
    private Board board;
    private Player p;
    private MessageCallback messageCallback;

    public GameController(Board board, MessageCallback messageCallback){
        this.board = board;
        this.messageCallback = messageCallback;
        this.p = findPlayer();
    }

    public Player findPlayer(){
        return board.getPlayer();
    }

    public void startGame(){

        while(isPlayerAlive()){
            tick();
            messageCallback.send(board.toString());
            //update map
        }
    }

    private void tick(){
        Position playerDesiredPos = p.move();

        Tile nextTile;

        if (!playerDesiredPos.equals(Position.at(-1,-1))) { //the player did not activate his special ability
            nextTile = board.get(playerDesiredPos.getX(), playerDesiredPos.getY());
            p.interact(nextTile);
        }
        p.processStep();

        List<Enemy> enemies = board.getEnemies();
        for(Enemy e: enemies) {
            e.updatePlayerPos(p.getPosition());
            Position enemyDesiredPos = e.move();
            nextTile = board.get(enemyDesiredPos.getX(), enemyDesiredPos.getY());
            e.interact(nextTile);
        }

    }

    public boolean isPlayerAlive(){
        return findPlayer() != null;
    }

    private boolean isLevelDone(){ //checks if all enemies are dead
        List<Tile> tiles = board.getTiles();
        for(Tile t: tiles)
            if(t.isEnemy())
                return false;
        return true;
    }
}
