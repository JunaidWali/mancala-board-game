package mancala;

public class InvalidMoveException extends Exception{

    public InvalidMoveException(){
        super("Move is invalid.");
    }
    
}