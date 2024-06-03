package mancala;

import java.io.Serializable;

public class Pit implements Countable, Serializable {

    private static final long serialVersionUID = 5958191702933113632L;
    private int numStones;

    /**
     * Initializes a new pit.
     */
    public Pit(){
        this.numStones = 0;
    }
    
    /**
     * Gets the number of stones in the pit.
     * @return The number of stones in the pit
     */
    public int getStoneCount(){
        return this.numStones;
    }

    /**
     * Adds a stone to the pit.
     */
    public void addStone(){
        numStones++;
    }

    /**
     * Adds a specified number of stones to the pit.
     * @param numToAdd The number of stones to add
     */
    public void addStones(int numToAdd){
        numStones += numToAdd;
    }

    /**
     * Removes and returns the stones from the pit.
     * @return The number of stones removed from the pit
     */ 
    public int removeStones(){
        int numRemoved = numStones;
        numStones = 0;

        return numRemoved;
    }

    @Override
    public String toString(){
        return "The pit has " + getStoneCount() + " stones.";
    }
    
}
