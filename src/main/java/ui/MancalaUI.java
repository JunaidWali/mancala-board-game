package ui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import mancala.AyoRules;
import mancala.InvalidMoveException;
import mancala.KalahRules;
import mancala.MancalaGame;
import mancala.PitNotFoundException;
import mancala.Player;
import mancala.Saver;
import mancala.UserProfile;



public class MancalaUI extends JFrame {

    private JPanel gameContainer;
    private JMenuBar menuBar;
    private PositionAwareButton[] buttons;
    private JTextField store1;
    private JTextField store2;
    private MancalaGame game;
    private Player player1;
    private Player player2;
    private Saver saver;

    public MancalaUI(String title) {
        super();
        basicSetUp(title);

    }

    private void basicSetUp(String title){
        game = new MancalaGame();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        saver = new Saver();
        game.setPlayers(player1, player2);
        this.setTitle(title);
        gameContainer = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setupGameContainer();
        add(gameContainer, BorderLayout.CENTER);
        add(makeButtonPanel(), BorderLayout.EAST);
        add(makeMancalaBoard(), BorderLayout.CENTER);
        makeMenu();
        setJMenuBar(menuBar);
        //makeGameOver();
        pack();
        
    }

    public static void main(String[] args) {
        MancalaUI gameUI = new MancalaUI("Mancala Game");
        gameUI.setVisible(true);
    }

    /************************
     *    GUI Components    *      
     ************************/
    

    private JPanel startupMessage() {
        JPanel message = new JPanel();
        message.add(new JLabel("Time to play Mancala!")); // Customize the message as desired
        return message;
    }

    public void setupGameContainer(){
        gameContainer.add(startupMessage());
    }

    private JPanel makeButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeKalahButton());
        buttonPanel.add(makeAyoButton());
        return buttonPanel;
    }

    private JButton makeKalahButton() {
        JButton button = new JButton("Play Kalah");
        button.addActionListener(e -> startKalah());
        return button;
    }

    
    private JButton makeAyoButton() {
        JButton button = new JButton("Play Ayo");
        button.addActionListener(e -> startAyo());
        return button;
    }


    private JPanel makeMancalaTopRow(int size, PositionAwareButton[] buttons) {

        // Make a JPanel with box layout that holds everything in the top row
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Player 1", JLabel.CENTER));

        // Make a GridLayout of Jabels for the top row
        JPanel topLabels = new JPanel();
        topLabels.setLayout(new GridLayout(1, size));
        for (int i = size; i > 0; i--) {
            topLabels.add(new JLabel(String.valueOf(i), JLabel.CENTER));
        }
        panel.add(topLabels);

        // Make a GridLayout of JButtons for the top row
        JPanel topPits = new JPanel();
        topPits.setLayout(new GridLayout(1, size));

        // Iterate over the data structure
        for (int i = size; i > 0; i--) {
            buttons[i - 1] = new PositionAwareButton();
            try {
                buttons[i - 1].setText(String.valueOf(game.getNumStones(i))); // Display the count of stones
            } catch (mancala.PitNotFoundException e) {
                // Handle the exception here
                e.getMessage();
            }
            buttons[i - 1].setPosition(i); // set position in the grid
            buttons[i - 1].addActionListener(e -> makeMove(e));
            topPits.add(buttons[i - 1]);
        }
        panel.add(topPits);

        return panel;
    }

    private JPanel makeMancalaBottomRow(int size, PositionAwareButton[] buttons) {

        // Make a JPanel with box layout that holds everything in the bottom row
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Make a GridLayout of JButtons for the top row
        JPanel topPits = new JPanel();
        topPits.setLayout(new GridLayout(1, size));

        // Iterate over the data structure
        for (int i = 0; i < size; i++) {
            buttons[i + size] = new PositionAwareButton();
            try {
                buttons[i + size].setText(String.valueOf(game.getNumStones(i + size + 1))); // Display the count of stones
            } catch (PitNotFoundException e) {
                e.getMessage();
            }
            buttons[i + size].setPosition(i + size + 1); // set position in the grid
            buttons[i + size].addActionListener(e -> makeMove(e));
            topPits.add(buttons[i + size]);
        }
        panel.add(topPits);

        // Make a GridLayout of Jabels for the top row
        JPanel topLabels = new JPanel();
        topLabels.setLayout(new GridLayout(1, size));
        for (int i = 0; i < size; i++) {
            topLabels.add(new JLabel(String.valueOf(i + size + 1), JLabel.CENTER));
        }
        panel.add(topLabels);


        panel.add(new JLabel("Player 2", JLabel.CENTER));

        return panel;
    }

    private JPanel makeMancalaBoard() {
        JPanel board = new JPanel();
        board.setLayout(new BorderLayout());
        buttons = new PositionAwareButton[12];

        // Add the top row of the board
        board.add(makeMancalaTopRow(6, buttons), BorderLayout.NORTH);
        // Add bottom row of the board
        board.add(makeMancalaBottomRow(6, buttons), BorderLayout.SOUTH);
        // Make the Player 1 store
        store1 = new JTextField();
        store1.setEditable(false);
        store1.setHorizontalAlignment(JTextField.CENTER);
        store1.setText(String.valueOf(player1.getStoreCount()));
        board.add(store1, BorderLayout.WEST);

        // Make the Player 2 store
        store2 = new JTextField();
        store2.setEditable(false);
        store2.setHorizontalAlignment(JTextField.CENTER);
        store2.setText(String.valueOf(player2.getStoreCount()));
        board.add(store2, BorderLayout.EAST);
        return board;
    }

    private void makeMenu() {
        menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> handleSaveGame());
        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(e -> handleLoadGame());  
        file.add(saveGame);
        file.add(loadGame);
        menuBar.add(file);

        JMenu view = new JMenu("View");
        JMenuItem profile1 = new JMenuItem("Player1 Profile");
        profile1.addActionListener(e -> handleProfile(player1.getProfile()));
        JMenuItem profile2 = new JMenuItem("Player2 Profile");
        profile2.addActionListener(e -> handleProfile(player2.getProfile()));
        view.add(profile1);
        view.add(profile2);
        menuBar.add(view);
    }

    private void makeGameOver() {
        String winner = null;
        if (game.getWinner() == 1) {
            winner = player1.getName();
        } else if (game.getWinner() == 2) {
            winner = player2.getName();
        }

        String message = "Game Over\n" + winner + " has won!\nWould you like to play again?";

        int response = JOptionPane.showOptionDialog(null, message, "Game Over",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{} , null);

        if (response == JOptionPane.YES_OPTION) {
            game.startNewGame();
        } else {
            System.exit(0);
        }

    }


    /************************
     *    Event Handlers    *      
     ************************/

    protected void updateBoard() {
        for (int i = 0; i < buttons.length; i++) {
            try {
                buttons[i].setText(String.valueOf(game.getNumStones(i + 1)));
            } catch (PitNotFoundException e) {
                e.getMessage();
            }
        }

        //update the stores
        store1.setText(String.valueOf(player1.getStoreCount()));
        store2.setText(String.valueOf(player2.getStoreCount()));

        store1.repaint();
        store2.repaint();

    }

    protected void startKalah(){
        game.setBoard(new KalahRules());
        game.startNewGame();
        updateBoard();
    }

    protected void startAyo() {
        game.setBoard(new AyoRules());
        game.startNewGame();
        updateBoard();
    }

    protected void makeMove(ActionEvent e) {
        PositionAwareButton button = (PositionAwareButton) e.getSource();
        int position = button.getPosition();
        try {
            game.move(position);
            updateBoard();
            if (game.isGameOver() == true) {
                makeGameOver();
            }
        } catch (InvalidMoveException err) {
            err.getMessage();
        }
        
    }

    protected void handleSaveGame() {
        String fileName = JOptionPane.showInputDialog("Enter the name of the file to save to");

        try {
            saver.saveObject(game, fileName);
            JOptionPane.showMessageDialog(null, "Game saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving game." + e.getMessage());
        }
    }

    protected void handleLoadGame() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                game = (MancalaGame) saver.loadObject(selectedFile.getPath());
                updateBoard();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading game." + e.getMessage());
            }
        }
        
    }

    protected void handleProfile(UserProfile profile) {
        String message = "Name: " + profile.getName() + "\n"
                        + "Kalah Games Played: " + profile.getNumKalahGamesPlayed() + "\n"
                        + "Kalah Games Won: " + profile.getNumKalahGamesWon() + "\n"
                        + "Ayo Games Played: " + profile.getNumAyoGamesPlayed() + "\n"
                        + "Ayo Games Won: " + profile.getNumAyoGamesWon() + "\n";
        JOptionPane.showMessageDialog(null, message, "Player 1 Profile", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
