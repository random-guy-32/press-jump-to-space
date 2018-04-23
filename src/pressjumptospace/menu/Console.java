package pressjumptospace.menu;

import javax.swing.*;

/**
 * The console window that shows messages to the player.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class Console {
    /**
     * The frame that holds the console.
     */
    public static JFrame menuFrame;

    /**
     * The text that marks the console.
     */
    public static JLabel title = new JLabel("Last Console Output:");

    /**
     * The last message that was sent to the console.
     */
    public static JLabel output = new JLabel("-----");

    /**
     * Sends a new message to the console.
     * @param s Message.
     */
    public static void print(String s) {
        Console.output.setText(s);
    }

    /**
     * Resets the console output.
     */
    public static void clear() {
        Console.output.setText("-----");
    }
}
