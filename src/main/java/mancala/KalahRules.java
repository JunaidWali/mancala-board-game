package mancala;

public class KalahRules extends GameRules {

    private static final long serialVersionUID = 6590317064585867117L;

    public KalahRules() {
        super();
    }


    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {

        // Validate the move
        if (!validateMove(startPit, playerNum)) {
            throw new InvalidMoveException();
        }

        // Number of stones in the player's store before
        int stonesBeforeMove = getDataStructure().getStoreCount(playerNum);

        // Set iterator
        getDataStructure().setIterator(startPit, getCurrentPlayer(), false);

        // Distribute stones
        distributeStones(startPit);



        if (getIteratorPos() != 6 || getIteratorPos() != 13) {
            // Capture stones
            captureStones(getStoppingPit());
        }


        // Number of stones added to player's store
        int stonesAdded = getDataStructure().getStoreCount(playerNum) - stonesBeforeMove;

        
        checkForFreeTurn(getStoppingPit());
        

        return stonesAdded;
    }

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    @Override
    int distributeStones(int startPit) {

        // Number of stones in the pit
        int stonesInPit = getDataStructure().removeStones(startPit);
        Countable currentSpot;

        // Distribute stones
        for (int i = 0; i < stonesInPit; i++) {
            currentSpot = getDataStructure().next();
            currentSpot.addStone();
        }

        return stonesInPit;
    }

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    @Override
    int captureStones(int stoppingPoint) {

        int stonesCaptured = 0;

        // Number of stones in stopping point
        if (getDataStructure().getNumStones(stoppingPoint) == 1) {
            
            if (getPlayerSide(stoppingPoint) == getCurrentPlayer()) {
                //Capture stones in stopping point
                stonesCaptured += getDataStructure().removeStones(stoppingPoint);
                // Capture stones in opposite pit
                int oppositePit = 13 - stoppingPoint;
                stonesCaptured += getDataStructure().removeStones(oppositePit);
                getDataStructure().addToStore(getCurrentPlayer(), stonesCaptured);
            }
        }

        return stonesCaptured;
    }

    /**
     * Checks for free turn
     * @param stoppingPoint
     */
    void checkForFreeTurn(int stoppingPoint) {
        if (stoppingPoint == 6 && getCurrentPlayer() == 1) {
            System.out.println("Free turn for player 1");
        } else if (stoppingPoint == 13 && getCurrentPlayer() == 2) {
            System.out.println("Free turn for player 2");
        } else {
            switchPlayer();
        }
    }

    /**
     * Switch the current player.
     */
    void switchPlayer(){
        if (getCurrentPlayer() == 1) {
            setPlayer(2);
        } else if (getCurrentPlayer() == 2) {
            setPlayer(1);
        }
    }

    
    
}
