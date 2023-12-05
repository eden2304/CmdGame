package com.company.Enemies;

import com.company.Position;

public class Monster extends Enemy {

    private int visionRange;

    public Monster(char tile,String name, int healCapacity, int attack, int defense, int experienceValue,
                   int visionRange)
    {
        super (tile, name, healCapacity, attack, defense, experienceValue);
        this.visionRange = visionRange;
    }

    @Override
    public Position move(){
        if (findRange(this.playerPos) < visionRange){
            Position playerP = this.playerPos;
            int dx = this.position.getX() - playerP.getX();
            int dy = this.position.getY() - playerP.getY();
            if (Math.abs(dx) > Math.abs(dy))
                if (dx > 0)
                    return this.position.moveLeft();
                else
                    return this.position.moveRight();
            else
                if (dy > 0)
                    return this.position.moveUp();
                else
                    return this.position.moveDown();
        }
        else{ //choosing randomly
            int[][] posUpdates = {{0,1},{0,-1},{-1,0},{1,0},{0,0}}; //up, down, left, right, nothing
            int moveNum = (int)(Math.random() * 5);
            int[] move = posUpdates[moveNum];
            Position moveP = Position.at(move[0],move[1]);
            return position.addPos(moveP);
        }
    }
}
