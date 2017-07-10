// the shit I put up with...
package pressjumptospace.main;

import pressjumptospace.entity.player.Controls;
import pressjumptospace.entity.player.Player;
import pressjumptospace.level.Level;
import pressjumptospace.menu.Console;
import pressjumptospace.menu.MenuFrame;
import pressjumptospace.menu.MetaMenu;
import pressjumptospace.render.GameCanvas;
import pressjumptospace.render.PaletteCanvas;

import javax.swing.JFrame;
import java.io.IOException;
import java.lang.InterruptedException;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class PressJumpToSpace {
    public static final String version = "1.4.2_a";

    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        // I hate all of this
        JFrame gameFrame = new JFrame("Press Jump to Space v" + version);
        GameCanvas gameCanvas = new GameCanvas(new Player(0, 0, (byte) 0, 1));

        JFrame paletteFrame = new JFrame("Tiles");
        PaletteCanvas paletteCanvas = new PaletteCanvas(256, 256);

        MetaMenu metaMenu = new MetaMenu();

        JFrame controlsFrame = new JFrame("Help: Controls");
        controlsFrame.setSize(400, 300);
        controlsFrame.setLocation(30, 30);

        JLabel controlsText = new JLabel();
        controlsText.setText(metaMenu.help.get("controls"));

        controlsFrame.add(new JScrollPane(controlsText), BorderLayout.CENTER);

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameFrame.getContentPane().add(gameCanvas);
        gameFrame.setResizable(true);
        gameCanvas.setPreferredSize(new Dimension(Game.OBJWIDTH, Game.OBJHEIGHT));
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocation(10, 10);
        gameFrame.setVisible(true);

        paletteFrame.getContentPane().add(paletteCanvas);
        paletteFrame.setResizable(true);
        paletteCanvas.setPreferredSize(new Dimension(paletteCanvas.objWidth, paletteCanvas.objHeight));
        paletteFrame.setResizable(false);
        paletteFrame.pack();
        paletteFrame.setLocation(30 + Game.OBJWIDTH, 10);
        paletteFrame.setVisible(true);

        metaMenu.menuFrame.setSize(MenuFrame.objWidth, MenuFrame.objHeight);
        metaMenu.menuFrame.setLocation(30 + Game.OBJWIDTH, 20 + paletteFrame.getHeight());
        metaMenu.menuFrame.setResizable(true);
        metaMenu.menuFrame.setVisible(true);

        Level.initiateChunks();
        Level.loadTileset();
        Level.loadEntityset();

        PaletteCanvas.loadTiles();
        PaletteCanvas.loadSpawners();
        paletteCanvas.repaint();

        Game.gameFrame = gameFrame;
        Game.paletteFrame = paletteFrame;
        Game.menuFrame = metaMenu.menuFrame;
        Game.controlsFrame = controlsFrame;

        Game.gameCanvas = gameCanvas;
        Game.paletteCanvas = paletteCanvas;

        Console.menuFrame = metaMenu.menuFrame;

        gameFrame.requestFocus();
        gameCanvas.setFocusable(true);
        gameCanvas.requestFocusInWindow();

        // FileManager.save("__test9");
        // FileManager.load("__test8");

        Game.tick();
    }
}