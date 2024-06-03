package mancala;

public class PitNotFoundException extends Exception{

    public PitNotFoundException(){
        super("Pit not found.");
        System.out.println("Invalid pit. Try again.");
        
    }
    
}
