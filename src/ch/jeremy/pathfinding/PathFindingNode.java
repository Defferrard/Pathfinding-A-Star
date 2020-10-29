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
public class PathFindingNode  implements Comparable<PathFindingNode>{
    private Case node;
    private PathFindingNode parent;
    private int g;

    public PathFindingNode(Case node, PathFindingNode parent) {
        this.node = node;
        this.parent = parent;
        if(parent != null){
            this.g = parent.getG()+1;
        }else{
            this.g = 1;
        }
    }

    public int getG() {
        return g;
    }

    public boolean isWalkable(){
        return node.isWalkable();
    }
    
    public int getX(){
        return node.getX();
    }
    public int getY(){
        return node.getY();
    }
    
    public Case getNode() {
        return node;
    }

    public void setNode(Case node) {
        this.node = node;
    }

    public PathFindingNode getParent() {
        return parent;
    }

    public void setParent(PathFindingNode parent) {
        if(parent != null){
            this.parent = parent;
            this.g = parent.getG()+1;
        }
    }

    @Override
    public int compareTo(PathFindingNode o) {
        return Integer.compare(g, o.getG());
    }
    
}
