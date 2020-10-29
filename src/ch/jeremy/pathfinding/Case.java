/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.jeremy.pathfinding;

/**
 *
 * @author Administrator
 */
public class Case {

    private boolean walkable;
    private int x;
    private int y;

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public Case(boolean walkable, int x, int y) {
        this.walkable = walkable;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

   

}
