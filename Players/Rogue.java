package com.company.Players;
import com.company.Enemies.Enemy;
import com.company.Position;
import com.company.Tiles.Unit;

import java.util.*;

public class Rogue extends Player
{
    private int cost;
    private int currEnergy;

    public Rogue (String name, int healCapacity, int attack, int defense, int cost)
    {
        super (name, healCapacity, attack, defense);
        this.cost = cost;
        this.currEnergy = 100;
        attackRange = 2;
    }

    public int getCurrEnergy(){return this.currEnergy;}
    public int getCost(){return this.cost;}

    @Override
    public Position move(){
        currEnergy = Math.min(currEnergy + 10,100);

        Position newPos = super.move();

        if (newPos.equals(Position.at(-1,-1))){ //activate special ability
            if (currEnergy >= cost){
                currEnergy -= cost;

                specialMove();
            }
            else
                throw new RuntimeException("Cannot cast special ability");
        }
        return newPos;
    }

    @Override
    public void specialMove(){
        List<Enemy> enemies = observer.findEnemiesInRange(attackRange);
        for (Enemy e: enemies)
            if (specialBattle(e, attack))
                processKilling(e);
    }

    public boolean specialBattle(Unit u, int damage){
        int opponentMove = u.defend();

        if (damage > opponentMove)
            u.acceptDamage(damage - opponentMove);

        return !u.isAlive();
    }

    @Override
    public void levelUp(){
        super.levelUp();
        currEnergy = 100;
        setAttack(attack + (3 * level));
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tenergy: %s", getCurrEnergy() + "/" + getCost());
    }
}
