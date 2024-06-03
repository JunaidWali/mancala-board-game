package mancala;

import java.io.Serializable;

public class Store implements Countable, Serializable {
        
    private static final long serialVersionUID = -1339094038746528051L;
    private Player owner;
    private int numStones;

    /**
     * Initializes a new store.
     */
    public Store(){
        numStones = 0;
    }

    /**
     * Sets the owner of the store.
     * @param player - The owner of the store
     */
    void setOwner(Player player){
        player.setStore(this);
        this.owner = player;
    }

    /**
     * Gets the owner of the store.
     * @return The owner of the store
     */
    public Player getOwner(){
        return this.owner;
    }

    /**
     * Adds stones to the store.
     * @param amount - The number of stones to add
     */
    public void addStones(int amount){
        numStones += amount;
    }


    /**
     * Empties the store and returns the number of stones that were in it.
     * @return The number of stones in the store
     */
    public int emptyStore(){
        int totalStones = getStoneCount();
        numStones = 0;
        return totalStones;
    }


    @Override
    public String toString(){
        return "This store belonging to " + this.getOwner().getName() + " has " + this.getStoneCount() + " stones.";
    }

    /**
     * Add one stone to the store.
     * @return The number of stones added
     */
    @Override
    public void addStone() {
        numStones++;
    }


    @Override
    public int getStoneCount() {
        return numStones;
    }


    @Override
    public int removeStones() {
        return emptyStore();
    }

}
