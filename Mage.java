package com.company;

import java.util.List;
import java.util.Random;

public class Mage extends Player
{
    private int manaPool;
    private int currMana;
    private int manaCost;
    private int spellPower;
    private int hitCounter;
    private int abilityRange;


    public Mage(String name, int healCapacity, int attack, int defense, int manaPool, int manaCost,
                int spellPower, int hitCounter,int abilityRange)
    {
        super (name, healCapacity, attack, defense);
        this.manaPool = manaPool;
        this.currMana = this.manaPool / 4;
        this.manaCost = manaCost;
        this.spellPower = spellPower;
        this.hitCounter = hitCounter;
        this.abilityRange = abilityRange;
        attackRange = this.abilityRange;
    }

    public int getSpellPower(){return this.spellPower;}
    public int getCurrMana() {return this.currMana;}
    public int getManaPool() {return this.manaPool;}


    public Position move(){
        currMana = Math.min(manaPool, currMana + level);

        Position newPos = super.move();

        if (newPos.equals(Position.at(-1,-1))){ //activate special ability
            if (currMana >= manaCost){
                currMana = currMana - manaCost;

                specialMove();
            }
            else
                throw new RuntimeException("Cannot cast special ability");
        }
        return newPos;
    }

    @Override
    public void specialMove(){
        int hits = 0;
        List<Enemy> enemies = observer.findEnemiesInRange(attackRange);

        while (hits < hitCounter && enemies.size() != 0){
            Random rand = new Random();
            Enemy e = enemies.get(rand.nextInt(enemies.size())); //choosing random enemy
            if (specialBattle(e,spellPower))
                processKilling(e);

            enemies = observer.findEnemiesInRange(attackRange);
            hits++;
        }
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
        manaPool += 25 * level;
        currMana = Math.min(currMana + manaPool/4,manaPool);
        spellPower += 10 * level;
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tMana: %d\t\tSpell Power: %s",getCurrMana() + "/" + getManaPool(),getSpellPower());
    }

}
