package mancala;

public class AyoRules extends GameRules {

    private static final long serialVersionUID = 8868613842003379656L;

    public AyoRules() {
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
        getDataStructure().setIterator(startPit, getCurrentPlayer(), true);

        distributeStones(startPit);
        while (getStoppingPitStones() != 1) {
            if (getIteratorPos() != 6 || getIteratorPos() != 13) {
                // Distribute stones
                distributeStones(getStoppingPit());
            }
        }


        if (getIteratorPos() != 6 || getIteratorPos() != 13) {
            // Capture stones
            captureStones(getStoppingPit());
        }

        // Number of stones added to player's store 

        return getDataStructure().getStoreCount(playerNum) - stonesBeforeMove;

    }

    /*
     * Helper method to get number of stones in iterator
     */
    /* default */ private int getStoppingPitStones() {
        return getDataStructure().getNumStones(getStoppingPit());
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

    @Override
    int captureStones(int stoppingPoint) {
        int stonesCaptured = 0;
        int numStones = getDataStructure().getNumStones(stoppingPoint);

        if (numStones == 1 && getPlayerSide(stoppingPoint) == getCurrentPlayer()) {

            int oppositePit = 13 - stoppingPoint;
            stonesCaptured += getDataStructure().removeStones(oppositePit);
            getDataStructure().addToStore(getCurrentPlayer(), stonesCaptured);
        }

        return stonesCaptured;
    }


}
