/*
package ui;

import java.util.Scanner;

import mancala.GameNotOverException;
import mancala.InvalidMoveException;
import mancala.MancalaGame;
import mancala.PitNotFoundException;
import mancala.Player;

public class TextUI {

    private MancalaGame game;
    private Scanner scanner;
    private Player player1;
    private Player player2;

    public TextUI() {
        game = new MancalaGame();
        scanner = new Scanner(System.in);
        player1 = new Player();
        player2 = new Player();
    }
    
    public static void main(String[] args) {
        
        TextUI ui = new TextUI();
        ui.start();
    
    }

    private void start() {

        //Welcome Message
        System.out.println("Welcome to Mancala!");

        //Ask for Player 1 name
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();

        //Ask for Player 2 name
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        player1.setName(player1Name);
        player2.setName(player2Name);
        game.setPlayers(player1, player2);

        game.startNewGame();

        while (!game.isGameOver()) {
            
            printBoard();

            int pitNumber = getPlayerMove();

            try {
                game.move(pitNumber);
                if (game.getBoard().getSwitchTurns()) {
                    game.switchPlayer();
                }
            } catch (InvalidMoveException e) {
                e.getMessage();
            }
            
        }

        printGameOver();
        scanner.close();

    }

    private void printBoard() {

        //Print header with player names and stores
        System.out.println();
        System.out.println("Player 1: " + player1.getName() + "\nStore: " + player1.getStoreCount());
        System.out.println("-----------------");
        System.out.println("Player 2: " + player2.getName() + "\nStore: " + player2.getStoreCount());

        // Print horizontal divider  
        System.out.println("-------------------------------------------------");
 
        // Print top row pits
        System.out.println("        " + player1.getName() + "'s side");
        System.out.print("    ");
        for (int i = 6; i > 0; i--) {
            System.out.printf("(%2s) ", i);
        }
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.print("|  ");

        for (int i = 5; i >= 0; i--) {
            try {
                System.out.print("|  ");
    		    System.out.printf("%-2s", game.getNumStones(i));
            } catch (PitNotFoundException e) {
                e.getMessage();
            }
        }

        System.out.print("|  |\n|");

        //print store number and row divider
        System.out.printf("%-2d|-----------------------------|%2d|\n", player1.getStoreCount(), player2.getStoreCount());


        //print bottom row pits
        System.out.print("|  ");

        for (int i = 6; i < 12; i++) {
            try {
                System.out.print("|  ");
    		    System.out.printf("%-2s", game.getNumStones(i));
            } catch (PitNotFoundException e) {
                e.getMessage();
            }
        }
        

        System.out.println("|  |");
        System.out.println("-------------------------------------");
        System.out.print("    ");
        for (int i = 7; i < 13; i++) {
            System.out.printf("(%2s) ", i);
        }
        System.out.println();
        System.out.println("        " + player2.getName() + "'s side");

    }

    private int getPlayerMove() {

        int pitNumber = -1;

        if (game.getCurrentPlayer() == player1) {
            System.out.println("\n" + game.getCurrentPlayer().getName() + "'s turn");
            System.out.print("Select a pit number (1-6): ");
            pitNumber = scanner.nextInt();
            while (pitNumber < 1 || pitNumber > 6) {
                System.out.print("Invalid pit choice. Enter between 1-6: ");
                pitNumber = scanner.nextInt();
            }
            
        } else if (game.getCurrentPlayer() == player2) {
            System.out.println("\n" + game.getCurrentPlayer().getName() + "'s turn");
            System.out.print("Select a pit number (7-12): ");
            pitNumber = scanner.nextInt();
            while (pitNumber < 7 || pitNumber > 12) {
                System.out.print("Invalid pit choice. Enter between 1-6: ");
                pitNumber = scanner.nextInt();
            }
        }

        if (game.getCurrentPlayer() == player1) {
            return pitNumber - 1;
        } else if (game.getCurrentPlayer() == player2) {
            return pitNumber - 1;
        }

        return 0;
    }

    private void printGameOver() {

        System.out.println("Game over!");

        try {
            if (game.getWinner() != null) {
                System.out.println("Congratulations! " + game.getWinner().getName() + " has won!");
                if (getRestartGame()) {
                    game.startNewGame();
                } else {
                    System.out.println("Goodbye!");
                }
            }
        } catch (GameNotOverException e) {
            e.getMessage();
        }

    }

    private boolean getRestartGame(){

        System.out.print("Do you want to restart the game? [Y/N]: ");
        char userInput = scanner.next().charAt(0);
        if (userInput == 'Y' || userInput == 'Y') {
            return true;
        } else if (userInput == 'N' || userInput == 'n') {
            return false;
        } else {
            System.out.println("Invalid input. Try again.");
            return getRestartGame();
        }
    }
    
}
*/