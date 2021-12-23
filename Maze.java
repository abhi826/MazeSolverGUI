package edu.njit.cs114;


import java.awt.*;

public class Maze {

    public static Color PATH = Color.green;
    public static Color BACKGROUND = Color.white;
    public static Color NON_BACKGROUND = Color.red;
    public static Color TEMPORARY = Color.black;

    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }


    public boolean findMazePath(int x, int y) {
        if (y >= maze.getNRows() || x >= maze.getNCols() || x < 0 || y < 0||maze.getColor(x,y)!=NON_BACKGROUND) {
            // no path from (x,y) to destination
            return false;
        }

        if(x==maze.getNRows()-1&&y==maze.getNCols()-1){
           maze.recolor(x,y,PATH);
            return true;
        }

        maze.recolor(x,y,TEMPORARY);
        boolean found = false;
        if(findMazePath(x+1,y)||findMazePath(x-1,y)||findMazePath(x,y+1)||findMazePath(x,y-1)){
            maze.recolor(x,y,PATH);
            found=true;
        }


        return found;
    }

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}
