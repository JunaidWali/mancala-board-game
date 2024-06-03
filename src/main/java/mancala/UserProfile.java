package mancala;

import java.io.Serializable;


/**
 * Represents a user profile in a game application.
 * The profile contains the user's name, the number of Kalah games they have played,
 * the number of Ayo games they have played, and the number of times they have won for each of those games.
 */
public class UserProfile implements Serializable {

    private static final long serialVersionUID = -1714910238253611662L;
    private String name;
    private int numKalahGamesPlayed;
    private int numAyoGamesPlayed;
    private int numKalahGamesWon;
    private int numAyoGamesWon;

    /**
     * Constructs a UserProfile object with the specified name.
     * Initializes the game statistics to zero.
     *
     * @param name the name of the user
     */
    public UserProfile(String name) {
        this.name = name;
        this.numKalahGamesPlayed = 0;
        this.numAyoGamesPlayed = 0;
        this.numKalahGamesWon = 0;
        this.numAyoGamesWon = 0;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the number of Kalah games played by the user.
     *
     * @return the number of Kalah games played
     */
    public int getNumKalahGamesPlayed() {
        return this.numKalahGamesPlayed;
    }

    /**
     * Returns the number of Ayo games played by the user.
     *
     * @return the number of Ayo games played
     */
    public int getNumAyoGamesPlayed() {
        return this.numAyoGamesPlayed;
    }

    /**
     * Returns the number of Kalah games won by the user.
     *
     * @return the number of Kalah games won
     */
    public int getNumKalahGamesWon() {
        return this.numKalahGamesWon;
    }

    /**
     * Returns the number of Ayo games won by the user.
     *
     * @return the number of Ayo games won
     */
    public int getNumAyoGamesWon() {
        return this.numAyoGamesWon;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the number of Kalah games played by the user.
     *
     * @param numKalahGamesPlayed the number of Kalah games played
     */
    public void setNumKalahGamesPlayed(int numKalahGamesPlayed) {
        this.numKalahGamesPlayed = numKalahGamesPlayed;
    }

    /**
     * Sets the number of Ayo games played by the user.
     *
     * @param numAyoGamesPlayed the number of Ayo games played
     */
    public void setNumAyoGamesPlayed(int numAyoGamesPlayed) {
        this.numAyoGamesPlayed = numAyoGamesPlayed;
    }

    /**
     * Sets the number of Kalah games won by the user.
     *
     * @param numKalahGamesWon the number of Kalah games won
     */
    public void setNumKalahGamesWon(int numKalahGamesWon) {
        this.numKalahGamesWon = numKalahGamesWon;
    }

    /**
     * Sets the number of Ayo games won by the user.
     *
     * @param numAyoGamesWon the number of Ayo games won
     */
    public void setNumAyoGamesWon(int numAyoGamesWon) {
        this.numAyoGamesWon = numAyoGamesWon;
    }
}
