package com.company;

public abstract class Tile {
    protected char tile;
    protected Position position;

    protected Tile(char tile){
        this.tile = tile;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void accept(Unit unit);

    public abstract boolean isEnemy();
    public abstract boolean isPlayer();
    public abstract Player getPlayerVersion();
    public abstract Enemy getEnemyVersion();

//    @Override
//    public int compareTo(Tile tile) {
//        return getPosition().compareTo(tile.getPosition());
//    }

    @Override
    public String toString() {
        return String.valueOf(tile);
    }

    public double findRange(Position pos)
    {
        return Math.abs(Math.sqrt(position.getX() - pos.getX()) +
                Math.sqrt(position.getY() - pos.getY()));
    }
}
