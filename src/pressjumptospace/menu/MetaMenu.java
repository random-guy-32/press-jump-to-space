package pressjumptospace.menu;

import pressjumptospace.main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

// the only interface you will be seeing in this program
public class MetaMenu implements ActionListener {
    // this is disgusting
    public Hashtable<String, String> help;

    public MetaMenu() {
        help = (new Help()).content;

        menuFrame = new MenuFrame("Menu & Console");

        container = menuFrame.getContentPane();

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        newFile = new JMenuItem("New File [doesn't work]");
        newFile.addActionListener(this);
        loadFile = new JMenuItem("Load File [doesn't work]");
        loadFile.addActionListener(this);
        saveFile = new JMenuItem("Save File [doesn't work]");
        saveFile.addActionListener(this);

        controlsHelp = new JMenuItem("Controls");
        controlsHelp.addActionListener(this);

        fileMenu.add(newFile);
        fileMenu.add(loadFile);
        fileMenu.add(saveFile);

        helpMenu.add(controlsHelp);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        menuFrame.add(menuBar, BorderLayout.NORTH);




        // controlsFrame.add(new JScrollPane(controlsText), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        panel.add(Console.title);
        panel.add(Console.output);

        JScrollPane pane = new JScrollPane(panel);

        menuFrame.add(pane, BorderLayout.CENTER);

        // menuFrame.add(Console.title);
        // menuFrame.add(Console.output);
    }

    public MenuFrame menuFrame;

    public Container container;

    public JMenuBar menuBar;

    public JMenu fileMenu;
    public JMenu helpMenu;

    public JMenuItem newFile;
    public JMenuItem saveFile;
    public JMenuItem loadFile;

    public JMenuItem controlsHelp;

    public void actionPerformed(ActionEvent object) {
        if (object.getSource() == controlsHelp) {
            Game.controlsFrame.setVisible(true);
        }
    }
}