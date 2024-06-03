package mancala;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = -213033726685782580L;
    private UserProfile profile;
    private Store playerStore;
    
    /**
     * Initializes a new player.
     */
    public Player(){
        this("Player");
    }

    /**
     * Initializes a new player with a name.
     * @param name - The player's name
     */
    public Player(String name){
        profile = new UserProfile(name);
        profile.setName(name);
    }

    /**
     * Gets the player's profile.
     * @return The player's profile
     */
    public UserProfile getProfile(){
        return this.profile;
    }

    
    /**
     * Gets the name of the player.
     * @return The player's name
     */
    public String getName(){
        return profile.getName();
    }

    /**
     * Sets the player's name.
     * @param name - The player's name
     */
    public void setName(String name){
        profile.setName(name);
    }

    /**
     * Gets the player's store where they collect stones.
     * @return The player's store
     */
    public Store getStore(){
        return this.playerStore;
    }

    /**
     * Gets the count of the number of stones in the player's store where they collect stones.
     * @return The count of stones in the player's store
     */
    public int getStoreCount(){
        return playerStore.getStoneCount();
    }

    /**
     * Sets the player's store.
     * @param store - The player's store
     */
    public void setStore(Store store){
        this.playerStore = store;
    }

    @Override
    public String toString(){
        return this.getName() + " has " + this.getStore().getStoneCount() + " stones in their store.";
    }


}
