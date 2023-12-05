package com.company;

public abstract class Enemy extends Unit
{
    private int experienceValue;
    protected Position playerPos;
    private boolean isAlive;

    public Enemy(char tile,String name, int healCapacity, int attack, int defense, int experienceValue)
    {
        super(tile, name, healCapacity, attack, defense);
        this.experienceValue = experienceValue;
        playerPos = new Position(0,0);
        isAlive = true;
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

    public void onDeath(){
        isAlive = false;
        tile = ','; //?
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
