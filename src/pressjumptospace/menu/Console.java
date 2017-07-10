package pressjumptospace.menu;

import javax.swing.*;

public class Console {
    public static JFrame menuFrame;
    public static JLabel title = new JLabel("Last Console Output:");
    public static JLabel output = new JLabel("-----");

    public static void print(String s) {
        Console.output.setText(s);
    }
    public static void clear() {
        Console.output.setText("-----");
    }
}
