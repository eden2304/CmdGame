package com.company.Players;
import com.company.Enemies.Enemy;
import com.company.Position;
import com.company.Tiles.Unit;

import java.util.Scanner;

public abstract class Player extends Unit
{
    private int experience;
    protected int level;
    private static char p = '@';
    private boolean isAlive;
    private final int expForLeveling;
    protected int attackRange;

    public Player(String name, int healCapacity, int attack, int defense)
    {
        super(p, name, healCapacity, attack, defense);
        this.experience = 0;
        this.level = 1;
        this.isAlive = true;
        this.expForLeveling = 50;
    }

    public boolean isAlive(){return isAlive;}
    public void setExperience(int experience){ this.experience = experience;}
    public int getlevel(){return this.level;}
    public int getexperience(){return this.experience;}

    public void levelUp() {
        experience -= 50 * level;
        level++;
        health.setHealthPool(health.getHealthPool() + 10 * level);
        health.setHealthAmount(health.getHealthPool());
        attack += 4 * level;
        defense += level;
    }

    public void accept(Unit unit){
        unit.visit(this);
    }

    public void visit(Player p){
        throw new RuntimeException("Player cannot visit another player");
    }

    public void visit(Enemy e){
        // trying to fight an enemy
        boolean isEnemyKilled = battle(e); // our turn
        if (isEnemyKilled)
            processKilling(e);
        else
            e.battle(this); // enemy turn
    }

    public void processKilling(Enemy e){
        this.experience += e.getExperienceValue();
        observer.killEnemy(e);
    }

    public void processStep(){
        if (experience >= expForLeveling * level)
            levelUp(); //automatically goes to level up of the child
    }

    @Override
    public void onDeath(){
        isAlive = false;
        p = 'X';
    }

    // for the superpower move
    public abstract void specialMove();
    public abstract boolean specialBattle(Unit u, int damage);

    @Override
    public Position move() {
        while (true) {
            Scanner input = new Scanner(System.in); //accept input from user

            char action = input.next().charAt(0); //convert input into char

            char specialAbility = 'e';
            char[] moves = {'w', 's', 'a', 'd', 'q'};
            int[][] posUpdates = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {0, 0}}; //up, down, left, right, nothing

            for (int i = 0; i < moves.length; i++) {
                if (moves[i] == action) {
                    Position move = Position.at(posUpdates[i][0], posUpdates[i][1]);
                    return position.addPos(move);
                } else if (action == specialAbility)
                    return Position.at(-1, -1); //means the player wants the special ability activated
            }
        }
    }

    @Override
    public String describe(){
        return super.describe() + String.format("\t\tlevel: %d\t\texperience: %d",getlevel(),getexperience());
    }

    @Override
    public boolean isEnemy() {return false;}
    @Override
    public Enemy getEnemyVersion() {return null;}
    @Override
    public boolean isPlayer() {return true;}
    @Override
    public Player getPlayerVersion() {return this;}
}
