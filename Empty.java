package com.company;

public class Empty extends Tile{

    private static final char dot = '.';

    public Empty(Position pos){
        super(dot);
        initialize(pos);
    }

    public void accept(Unit unit){
        unit.visit(this);
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
