/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.jeremy.pathfinding;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Administrator
 */
public class PathFinding {

    private final int destX;
    private final int destY;
    private PathFindingNode currentCase;
    private final ArrayList<PathFindingNode> openList;
    private final ArrayList<Case> closeList;
    private final PathFindingNode[][] map;
    private final int width;
    private final int height;

    public PathFinding(Case[][] m, int sourceX, int sourceY, int destX, int destY) {
        //Initialisation de l'openList et de la closeList
        this.openList = new ArrayList<>();
        this.closeList = new ArrayList<>();

        //Coordonné de la destination dans les attributs
        this.destX = destX;
        this.destY = destY;

        //Taille de la map dans les attributs
        this.width = m.length;
        this.height = m[0].length;

        //Deffinition de la case de destination
        Case destCase = m[destX][destY];
        
        //Creation d'une copie de la map
        this.map = new PathFindingNode[width][height];

        //Initialisation de toute les case sur la copie, tout leurs parents sont null
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                this.map[i][j] = new PathFindingNode(m[i][j], null);
            }
        }
        
        //Initialisation de la première case courante qui est la case source
        currentCase = new PathFindingNode(m[sourceX][sourceY], null);
        map[sourceX][sourceY]= currentCase;
        
        //Ajout de la case courante dans l'openList
        openList.add(currentCase);
        

        //Tant que la case courante n'est pas la case de destination
        while (destCase != currentCase.getNode()) {           
            openList.remove(currentCase);
            closeList.add(currentCase.getNode());
            
            //Pour chaque case adjacent
            for (PathFindingNode node : getAdjacent(currentCase)) {   
                //S'il ne se trouve pas deja dans l'openList,
                //On lui donne comme parent la case courante,
                //et on l'ajoute dans l'openList
                if(!contain(node)){
                    node.setParent(currentCase);
                    openList.add(node);
                }else
                
                //Sinon si son G est plus grand qu'avec celui de la case courante,
                //On change son parent.
                if (node.getG() > currentCase.getG()+1) {
                    node.setParent(currentCase);
                }
            }
            
            //Si l'openList est vide, le path est impossible
            //On passe la current case à null, afin de ne pas rendre un travail mal fait
            if (openList.isEmpty()) {
                System.out.println("PATH IMPOSSIBLE");
                currentCase = null;
                return;
            }
            
            Collections.sort(openList);
            currentCase = openList.get(0);
        }
    }

    private int calcF(int x, int y) {
        int resultX = this.destX - x;
        int resultY = this.destY - y;
        if (resultX < 0) {
            resultX *= -1;
        }
        if (resultY < 0) {
            resultY *= -1;
        }
        return resultX + resultY;
    }

    private ArrayList<PathFindingNode> getAdjacent(PathFindingNode currentNode) {
        ArrayList<PathFindingNode> result = new ArrayList<>();
        PathFindingNode node;

        int x = currentNode.getX();
        int y = currentNode.getY();

        if (x > 0) {
            node = map[x - 1][y];
            if (node.isWalkable() && !closeList.contains(node.getNode()) && node != null) {
                result.add(node);
            }
        }
        if (x < width - 1) {
            node = map[x + 1][y];
            if (node.isWalkable() && !closeList.contains(node.getNode()) && node != null) {
                result.add(node);
            }
        }
        if (y > 0) {
            node = map[x][y - 1];
            if (node.isWalkable() && !closeList.contains(node.getNode()) && node != null) {
                result.add(node);
            }
        }
        if (y < height - 1) {
            node = map[x][y + 1];
            if (node.isWalkable() && !closeList.contains(node.getNode()) && node != null) {
                result.add(node);
            }
        }
        return result;
    }

    private boolean contain(PathFindingNode node) {
        return openList.stream().anyMatch((pathFindingNode) -> (pathFindingNode == node));
    }

    public PathFindingNode getLastCase() {
        return currentCase;
    }

}
