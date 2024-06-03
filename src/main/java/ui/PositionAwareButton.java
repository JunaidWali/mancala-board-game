package ui;

import javax.swing.JButton;

/**
 * Represents a GUI button component that knows its position in a grid.
 */
public class PositionAwareButton extends JButton {
    private int pos;


    /**
     * Constructs a new PositionAwareButton.
     */
    public PositionAwareButton() {
        super();
    }

    /**
     * Constructs a new PositionAwareButton with the specified text.
     *
     * @param val The text to display on the button.
     */
    public PositionAwareButton(String val) {
        super(val);
    }

    /**
     * Gets the vertical position (down) of the button in a grid.
     *
     * @return The vertical position of the button.
     */
    public int getPosition() {
        return pos;
    }


    /**
     * Sets the vertical position (down) of the button in a grid.
     *
     * @param val The vertical position to set.
     */
    public void setPosition(int val) {
        pos = val;
    }
}
