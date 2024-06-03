package mancala;


public class NoSuchPlayerException extends Exception{
    
    public NoSuchPlayerException(){
        super("Player does not exist.");        
    }
}
