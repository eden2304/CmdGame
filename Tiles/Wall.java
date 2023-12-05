package com.company.Tiles;

import com.company.Enemies.Enemy;
import com.company.Players.Player;
import com.company.Position;
import com.company.Tiles.Tile;
import com.company.Tiles.Unit;

public class Wall extends Tile {
    private static final char wall = '#';

    public Wall(Position pos){
        super(wall);
        initialize(pos);
    }

    public void accept(Unit unit){

    }

    @Override
    public boolean isEnemy(){return false;}
    @Override
    public Enemy getEnemyVersion() {return null;}
    @Override
    public boolean isPlayer() {return false;}
    @Override
    public Player getPlayerVersion() {return null;}
}
