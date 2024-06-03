package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {

    private static final long serialVersionUID = -1543563228688437674L;

    private MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    /* default */ MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }  

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    /* default */ boolean isSideEmpty(int pitNum) {
        int start = pitNum < 7 ? 1 : 7;
        int end = pitNum < 7 ? 7 : 13;
        
        for (int i = start, count = 0; i < end; i++) {
            if (getDataStructure().getNumStones(i) == 0) {
                count++;
            }
            if (count == 6) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* default */ abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* default */ abstract int captureStones(int siteratorPos);

    /*
     * Wrapper method to get position of iterator
     */
    public int getIteratorPos() {
        return gameBoard.getIteratorPos();
    }

    /*
     * Wrapper method to get stopping point
     */
    public int getStoppingPit() {
        return gameBoard.getStoppingPit();
    }


    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(Player one, Player two) {
        // this method can be implemented in the abstract class.


        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/

        Store store1 = new Store();
        store1.setOwner(one);
        gameBoard.setStore(store1, 1);
        Store store2 = new Store();
        store2.setOwner(two);
        gameBoard.setStore(store2, 2);

    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    /**
     * Validates the move based on the player number and the pit number.
     * @param startPit
     * @param playerNum
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(int startPit, int playerNum) {
        boolean isValid = false;

        if (startPit > 0 && startPit < 7 && playerNum == 1 
        || startPit > 6 && startPit < 13 && playerNum == 2) {
            isValid = true;
        }

        return isValid;
    }

    /**
     * Helper method to get the player number (1 or 2) for a given pit.
     * @param pitNum
     * @return The player number (1 or 2) for the given pit.
     */
    public int getPlayerSide(int pitNum) {
        int playerSide = 0;

        if (pitNum > 0 && pitNum < 7) {
            playerSide = 1;
        } else if (pitNum > 6 && pitNum < 13) {
            playerSide = 2;
        }

        return playerSide;
    }

    /**
     * Checks if the game is over.
     * @return True if the game is over, false otherwise
     */
    /* default */ boolean isGameOver(){
        
        if (isSideEmpty(0) || isSideEmpty(11)) {
             return true;
         } 
 
        return false;
    }

    /**
     * Gets the winner of the game.
     * @return The winning player (1 or 2) or 0 for a tie
     * @throws GameNotOverException
     */
    public int getWinner() throws GameNotOverException {
        int winner = 0;

        if (!isGameOver()) {
            throw new GameNotOverException();
        } else {
            if (gameBoard.getStoreCount(1) > gameBoard.getStoreCount(2)) {
                winner = 1;
            } else if (gameBoard.getStoreCount(2) > gameBoard.getStoreCount(1)) {
                winner = 2;
            }
        }

        return winner;
    }


    @Override
    public String toString() {
        // Implement toString() method logic here.
        return gameBoard.toString() + "\n" + "Current Player: " + getCurrentPlayer();
    }
}
