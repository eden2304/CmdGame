package com.company.Enemies;

import com.company.MessageCallback;
import com.company.Observer;
import com.company.Players.Player;
import com.company.Position;
import com.company.Tiles.Unit;

public abstract class Enemy extends Unit
{
    private final int experienceValue;
    protected Position playerPos;
    private boolean isAlive;

    public Enemy(char tile,String name, int healCapacity, int attack, int defense, int experienceValue)
    {
        super(tile, name, healCapacity, attack, defense);
        this.experienceValue = experienceValue;
        playerPos = new Position(0,0);
        isAlive = true;
    }

    public void Initialize(Position position, MessageCallback messageCallback, Observer observer){
        super.initialize(position,messageCallback,observer);
    }

    public void updatePlayerPos(Position pos){
        playerPos = pos;
    }
    public int getExperienceValue() {return this.experienceValue;}
    public boolean isAlive(){return isAlive;}

    public void accept(Unit unit){
        unit.visit(this);
    }
    public void visit(Player p){ //combat against the player
        battle(p);
    }
    public void visit(Enemy e){/*cannot go there so empty method*/}

    public void processStep(){}

    @Override
    public void onDeath(){
        isAlive = false;
        tile = ','; // just in case?
    }

    public abstract Position move();

    @Override
    public boolean isEnemy(){return true;}
    @Override
    public Enemy getEnemyVersion() {return this;}
    @Override
    public boolean isPlayer() {return false;}
    @Override
    public Player getPlayerVersion() {return null;}
}
