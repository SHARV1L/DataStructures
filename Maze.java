package Maze;

import java.util.ArrayList;


/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /**
     * The maze
     */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /**
     * Wrapper method.
     */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     *
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     * otherwise, false
     * @pre Possible path cells are in BACKGROUND color;
     * barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     * PATH color; all cells that were visited but are4
     * not on the path are in the TEMPORARY color.
     */
//    public boolean findMazePath(int x, int y) {     //  function returns true if a path is found
//        // COMPLETE HERE FOR PROBLEM 1
//        if (x >= 0 && y > 0 || x < maze.getNCols() && y < maze.getNRows() && maze.getColor(x, y) == NON_BACKGROUND) {
//            if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
//                maze.recolor(x, y, PATH);
//                return true;
//            } else {
//                maze.recolor(x, y, PATH);
//                if (findMazePath(x + 1, y) || findMazePath(x - 1, y) || findMazePath(x, y + 1) || findMazePath(x, y - 1)) {
//                    return true;
//                } else {
//                    maze.recolor(x, y, TEMPORARY);
//                    return false;
//                }
//            }
//        } else {
//            return false;
//        }
//    }

    public boolean findMazePath(int x, int y) {     //  function returns true if a path is found
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) == TEMPORARY ||maze.getColor(x, y) == BACKGROUND ) {
            return false;
        }

        else if (x == maze.getNCols()-1 && y == maze.getNRows()-1) {
            maze.recolor(x, y, PATH);
            return true;
        }
        else if (maze.getColor(x, y) == NON_BACKGROUND){
            maze.recolor(x, y, TEMPORARY);
            if (this.findMazePath(x+1,y) || this.findMazePath(x-1,y) || this.findMazePath(x,y+1) || this.findMazePath(x,y-1) ) {
                maze.recolor(x, y, PATH);
                return true;
            }
        }

        return this.findMazePath(x+1,y) || this.findMazePath(x-1,y)  || this.findMazePath(x,y+1) || this.findMazePath(x,y-1);

    }

        // ADD METHOD FOR PROBLEM 2 HERE
        public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){    // a list of all the solutions to the maze is returned.
                                                                                // Each solution may be represented as a list of coordinates
            if(findMazePath()){
                maze.recolor(PATH, NON_BACKGROUND);
                maze.recolor(TEMPORARY, NON_BACKGROUND);
                return findAllMazePathHelp(0,0, new ArrayList<PairInt>());
            }
            else{
                maze.recolor(PATH, NON_BACKGROUND);
                maze.recolor(TEMPORARY, NON_BACKGROUND);
                ArrayList<PairInt> empty = new ArrayList<PairInt>();
                ArrayList<ArrayList<PairInt>> ret = new ArrayList<ArrayList<PairInt>>();
                ret.add(empty);
                return ret;
            }
        }

        public ArrayList<ArrayList<PairInt>> findAllMazePathHelp(int x, int y, ArrayList<PairInt> current){   // helping function for the method 'findAllMazePath'
            if( x < maze.getNCols() &&  y < maze.getNRows() && y >= 0 && maze.getColor(x,y).equals(NON_BACKGROUND)){
                current.add(new PairInt(x, y));
                if(x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)){
                    ArrayList<ArrayList<PairInt>> answer =new ArrayList<ArrayList<PairInt>>();
                    @SuppressWarnings("Unchecked")
                            ArrayList<PairInt> currentCopy = (ArrayList<PairInt>) current.clone();
                    answer.add(currentCopy);
                    current.remove(new PairInt(x,y));
                    return answer;
                }
                else{
                    maze.recolor(x,y,PATH);
                    ArrayList<ArrayList<PairInt>> right = findAllMazePathHelp(x+1, y, current);
                    ArrayList<ArrayList<PairInt>> left = findAllMazePathHelp(x-1,y, current);
                    ArrayList<ArrayList<PairInt>> up = findAllMazePathHelp(x, y+1, current);
                    ArrayList<ArrayList<PairInt>> down = findAllMazePathHelp(x, y-1, current);
                    ArrayList<ArrayList<PairInt>> answer = new ArrayList<ArrayList<PairInt>>();
                    answer.addAll(right);
                    answer.addAll(left);
                    answer.addAll(up);
                    answer.addAll(down);
                    maze.recolor(x,y, NON_BACKGROUND);
                    current.remove(new PairInt(x,y));
                    return answer;
                }
            }
            else{
                return new ArrayList<ArrayList<PairInt>>();
            }
        }

        // ADD METHOD FOR PROBLEM 3 HERE

        public ArrayList<PairInt> findMazePathMin(int x, int y){         // function returns the shortest path in the list of paths
            maze.recolor(PATH, NON_BACKGROUND);
            ArrayList<ArrayList<PairInt>> ans = findAllMazePaths(x,y);
            if(ans.size() != 0){
                ArrayList<PairInt> min = ans.get(0);
                int minLength = min.size();
                for(int i = 1; i< ans.size(); i++){
                    if(minLength >= ans.get(i).size()){
                        min = ans.get(i);
                        minLength = min.size();
                    }
                }
                return min;
            }
            else{
                return new ArrayList<PairInt>();
            }
        }

        /*<exercise chapter="5" section="6" type="programming" number="2">*/
        public void resetTemp () {
            maze.recolor(TEMPORARY, BACKGROUND);
        }
        /*</exercise>*/

        /*<exercise chapter="5" section="6" type="programming" number="3">*/
        public void restore () {
            resetTemp();
            maze.recolor(PATH, BACKGROUND);
            maze.recolor(NON_BACKGROUND, BACKGROUND);
        }
}
