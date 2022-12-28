package Maze;

public class PairInt {
    //for X coordinate
    private int x;

    //for Y coordinate
    private int y;

    //Constructors
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Member Functions

    public int getX() {     // for getting value of X
        return x;
    }

    public int getY() {     // for getting value of Y
        return y;
    }

    // Initializing X coordinates
    public void setX(int x){
        this.x = x;
    }

    // Initializing Y coordinates
    public void setY(int y) {
        this.y = y;
    }

    // for comparing current coordinates with the Object P
    public boolean equals(Object p) {
        if(!(p instanceof PairInt)){
            return false;
        }
        else{
            PairInt P = (PairInt)p;
            return P.x == this.x && P.y ==this.y;
        }
    }

    public String toString() {
        return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
    }

    // for creating a copy for current coordinates
    public PairInt copy() {
        PairInt answer = new PairInt(x,y);
        return answer;
    }
}