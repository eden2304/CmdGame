package com.company;
import java.util.*;

public class Warrior extends  Player
{
    private int cooldown;
    private int remainingCooldown;

    public Warrior (String name, int healCapacity, int attack, int defense, int cooldown)
    {
        super(name, healCapacity, attack, defense);
        this.cooldown = cooldown;
        this.remainingCooldown = 0;
        attackRange = 3;
    }

    public int getCooldown(){return  this.cooldown;}
    public int getRemainingCooldown(){return this.remainingCooldown;}

    @Override
    public Position move(){
        remainingCooldown -= 1;

        Position newPos = super.move();

        if (newPos.equals(Position.at(-1,-1))){ //activate special ability
            if (remainingCooldown <= 0){
                remainingCooldown = cooldown;
                int currHealth = health.getHealthAmount();
                int maxHealth = health.getHealthPool();
                getHealth().setHealthAmount(Math.min(currHealth + (10 * defense),maxHealth));

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
        Random rand = new Random();
        Enemy e = enemies.get(rand.nextInt(enemies.size())); //choosing random enemy
        if (specialBattle(e, (int)(health.getHealthPool() * 0.1)))
            processKilling(e);
    }

    public boolean specialBattle(Unit u, int damage){
        u.acceptDamage(damage);

        return !u.isAlive();
    }

    @Override
    public void levelUp(){
        super.levelUp();
        remainingCooldown = 0;
        health.setHealthPool(health.getHealthPool() + 5 * level);
        setAttack(attack + (2 * level));
        setDefense(defense + level);
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tcooldown: %s",getRemainingCooldown() + "/" + getCooldown());
    }

}
