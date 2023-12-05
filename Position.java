package com.company;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public static Position at(int x, int y){
        return new Position(x,y);
    }

    public void SetPosition(Position pos){
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int compareTo(Position pos){
        int yCompare = Integer.compare(y,pos.getY());
        if (yCompare != 0)
            return yCompare;
        return Integer.compare(x,pos.getX());
    }

    public Position addPos(Position pos){
        return new Position(x + pos.getX(), y + pos.getY());
    }

    public Position moveUp(){return new Position(this.x, this.y + 1);}
    public Position moveDown(){return new Position(this.x, this.y  - 1);}
    public Position moveLeft(){return new Position(this.x - 1, this.y);}
    public Position moveRight(){return new Position(this.x + 1, this.y);}
}
