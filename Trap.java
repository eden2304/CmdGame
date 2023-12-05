package com.company;

public class Trap extends  Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int tickCounter;
    private boolean visible;

    public Trap(char tile,String name, int healCapacity, int attack, int defense, int experienceValue,
                int visibilityTime, int invisibilityTime)
    {
        super(tile, name, healCapacity, attack, defense, experienceValue);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.tickCounter = 0;
        this.visible = true;
    }

    @Override
    public Position move(){
        visible = tickCounter < visibilityTime;
        if (tickCounter == (visibilityTime + invisibilityTime))
            tickCounter = 0;
        else
            tickCounter++;

        if (findRange(this.playerPos) < 2)
            battle(observer.getPlayer());

        return position;
    }



}