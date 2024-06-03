package mancala;

import java.io.Serializable;

public class MancalaGame implements Serializable {

    private static final long serialVersionUID = 8088135977828479540L;
    //private ArrayList<Player> players;
    private Player currentPlayer;
    private GameRules rules;

    //Initializes a new Mancala game.
    public MancalaGame(){
        //players = new ArrayList<>();
        rules = new KalahRules(); // Kalah rules is the default
        
    }
    
    /**
     * Sets the players for the game.
     * @param onePlayer
     * @param twoPlayer
     */
    public void setPlayers(Player onePlayer, Player twoPlayer){
        //players.add(onePlayer);
        //players.add(twoPlayer);
        rules.registerPlayers(onePlayer, twoPlayer);
        //this.setCurrentPlayer(players.get(0));
    }

    /**
     * Gets the players for the game.
     * @return An ArrayList of the players in the game
     */
    /* public ArrayList<Player> getPlayers(){
        return this.players;
    } */

    /**
     * Gets the current player
     * @return Gets the current player.
     */
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * sets the current player
     * @param player
     */
    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }

    /**
     * Sets the board for the game.
     * @param theBoard
     */
    public void setBoard(GameRules theRules){
        this.rules = theRules;
    }

    /**
     * Gets the board for the game.
     * @return
     */
    public GameRules getBoard(){
        return this.rules;
    }

    public boolean validatePit(int pitNum){
        if (pitNum > 0 || pitNum < 13) {
            return true;
        } else {
            return false;
           
        }
    }

    /**
     * Gets the number of stones in a specific pit.
     * @param pitNum
     * @return The number of stones in the pit
     * @throws PitNotFoundException
     */
    public int getNumStones(int pitNum) throws PitNotFoundException{
        
        if (validatePit(pitNum)) {
            return rules.getNumStones(pitNum);
        } else {
            throw new PitNotFoundException();
        }
        
    }

    /**
     * Makes a move for the current player.
     * @param startPit
     * @return the total number of stones remaining in the players side pits
     * @throws InvalidMoveException
     */
    public int move(int startPit) throws InvalidMoveException{
        
        if (!validatePit(startPit)) {
            throw new InvalidMoveException();
        } 
        
        rules.moveStones(startPit, rules.getCurrentPlayer());
        
        
        return rules.getNumStones(startPit);
    }


   /*  public void switchPlayer(){

        if (getCurrentPlayer() == getPlayers().get(0)) {
            setCurrentPlayer(getPlayers().get(1));
        } else if (getCurrentPlayer() == getPlayers().get(1)) {
            setCurrentPlayer(getPlayers().get(0));
        }

    } */

    
    /**
     * Helper method to calculate the total number of stones in current player's side
     * @return the total number of stones in current player's side
     */
    /*int getPlayerStoneCount(){
        int total = 0;
        if (getCurrentPlayer() == getPlayers().get(0)) {
            for (int i = 0; i < 6; i++) {
                total += getBoard().getNumStones(i);
            }
        } else if (getCurrentPlayer() == getPlayers().get(1)) {
            for (int i = 6; i < 12; i++) {
                total += getBoard().getNumStones(i);
            }
        }

        return total;
    }*/

    /**
     * Gets the total number of stones in a player's store.
     * @param player
     * @return The total number of stones in the player's store
     * @throws NoSuchPlayerException
     */
    /* public int getStoreCount(Player player) throws NoSuchPlayerException{
        
        if (!players.contains(player)) {
            throw new NoSuchPlayerException();    // Used AI for the logic
        }

        return player.getStoreCount();
    } */

   /*  public int getPlayerStoreCount(int playerNum){
        return rules.;
    } */

    /**
     * Gets the winner of the game.
     * @return The winning player or null for a tie
     * @throws GameNotOverException
     */
    public int getWinner() {
        try {
            return rules.getWinner();
        } catch (GameNotOverException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /*
     * Wrapper method to get isGameOver
     */
    public boolean isGameOver(){
        return rules.isGameOver();
    }


    /**
     * Starts a new game by resetting the board.
     */
    public void startNewGame(){
        rules.resetBoard();
    }

    /**
     * Generates a string representation of the game.
     */
    /* @Override
    public String toString(){
        return "This game has " + getPlayers().size() + " players and a board";
    } */


}
