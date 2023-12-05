package com.company;
import com.company.Enemies.Enemy;
import com.company.Players.Player;
import com.company.Tiles.Empty;
import com.company.Tiles.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class Board
{
    private List<Tile> tiles;

    public Board(Tile[][] board)
    {
        tiles = new ArrayList<>();
        for(Tile[] line : board)
            tiles.addAll(Arrays.asList(line));
    }

    public Tile get(int x, int y) {
        return tiles.stream().filter((t) -> t.getPosition().equals(Position.at(x,y))).toList().get(0);
    }

    public void remove(Tile e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for(Tile t : tiles){
            if(t.getPosition().getX() == 0){
                sb.append("\n");
            }
            sb.append(t);
        }
        return sb.toString();
    }

    public List<Tile> getTiles() {return tiles;}

    public List<Enemy> getEnemies(){
        List<Enemy> enemies = tiles.stream().filter(Tile::isEnemy).map(Tile::getEnemyVersion).toList();
        return enemies;

    }

    public Player getPlayer()
    {
        return tiles.stream().filter(Tile::isPlayer).map(Tile::getPlayerVersion).toList().get(0);
    }

}
