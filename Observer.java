package com.company;
import com.company.Enemies.Enemy;
import com.company.Players.Player;
import com.company.Tiles.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class Observer {
    private Board b;
    private Player p;

    public Observer(Board b){
        this.b = b;
        this.p = b.getPlayer();
    }

    public List<Enemy> findEnemiesInRange(int range){
        return b.getEnemies().stream().filter((e) -> e.findRange(p.getPosition())==range).collect(Collectors.toList());
    }

    public Player getPlayer(){return this.p;}

    public void killEnemy(Tile t){
        b.remove(t);
    }
}
