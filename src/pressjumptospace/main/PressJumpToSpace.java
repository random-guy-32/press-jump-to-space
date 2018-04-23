// the shit I put up with...
package pressjumptospace.main;

import pressjumptospace.entity.player.Player;
import pressjumptospace.level.Level;
import pressjumptospace.menu_old.Console;
import pressjumptospace.menu_old.MenuFrame;
import pressjumptospace.menu_old.MetaMenu;
import pressjumptospace.render.GameCanvas;
import pressjumptospace.render.PaletteCanvas;

import javax.swing.JFrame;
import java.io.IOException;
import java.lang.InterruptedException;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;

/**
 * Wrapper (?) class for initialization and stuffz.
 *
 * @author Charlotte Buff
 * @version 1.5
 */

public class PressJumpToSpace {
    /**
     * Current version number of the game.
     * Used in the frame title.
     */
    public static final String version = "1.5";

    /**
     * Main function and things.
     * Sets up all important game variables and the UI.
     *
     * @param args Unused.
     * @throws IOException I don't know.
     * @throws InterruptedException I don't know.
     * @throws IllegalAccessException I don't know.
     * @throws InstantiationException I don't know.
     * @throws ClassNotFoundException I don't know.
     */
    public static void main(String[] args) throws IOException, InterruptedException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        // I hate all of this

        // Main frame with the game's name in the title.
        JFrame gameFrame = new JFrame("Press Jump to Space v" + version);

        // Main canvas for the game field to render.
        // For some reason the player character is saved as part of the canvas.
        GameCanvas gameCanvas = new GameCanvas(new Player(0, 0, (byte) 0, 1));





        // Palette window and canvas.
        JFrame paletteFrame = new JFrame("Tiles");
        PaletteCanvas paletteCanvas = new PaletteCanvas(256, 256);

        // I forgot what this does.
        MetaMenu metaMenu = new MetaMenu();

        // Help frame where the controls of the game are explained.
        JFrame controlsFrame = new JFrame("Help: Controls");
        controlsFrame.setSize(400, 300);
        controlsFrame.setLocation(30, 30);

        // Ah, that's what MetaMenu is used for.
        // The MetaMenu class saves all the different help and menu texts.
        JLabel controlsText = new JLabel();
        controlsText.setText(metaMenu.help.get("controls"));

        // Appending the help text to the help window.
        controlsFrame.add(new JScrollPane(controlsText), BorderLayout.CENTER);

        // Game is closed when the red X is pressed.
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main canvas is appended to the main frame.
        gameFrame.getContentPane().add(gameCanvas);

        // Setting main canvas's size and position to the desired dimensions.
        gameFrame.setResizable(true);
        gameCanvas.setPreferredSize(new Dimension(Game.LEVEL_WIDTH, Game.LEVEL_HEIGHT));
        gameFrame.setResizable(false);
        gameFrame.pack();   // Very important.
        gameFrame.setLocation(10, 10);
        gameFrame.setVisible(true);

        gameCanvas.guiRoot.width = gameCanvas.getWidth();
        gameCanvas.guiRoot.height = gameCanvas.getHeight();

        // Doing the same thing for the palette window.
        paletteFrame.getContentPane().add(paletteCanvas);
        paletteFrame.setResizable(true);
        paletteCanvas.setPreferredSize(new Dimension(paletteCanvas.objWidth, paletteCanvas.objHeight));
        paletteFrame.setResizable(false);
        paletteFrame.pack();
        paletteFrame.setLocation(30 + Game.LEVEL_WIDTH, 10);
        paletteFrame.setVisible(true);

        // And for the menu window.
        metaMenu.menuFrame.setSize(MenuFrame.objWidth, MenuFrame.objHeight);
        metaMenu.menuFrame.setLocation(30 + Game.LEVEL_WIDTH, 20 + paletteFrame.getHeight());
        metaMenu.menuFrame.setResizable(true);
        metaMenu.menuFrame.setVisible(true);

        // Prepares the playing field.
        // Prepares active tiles and entity spawners.
        Level.initiateChunks();
        Level.loadTileset();
        Level.loadEntityset();

        // Adds all buttons to the palette.
        PaletteCanvas.loadTiles();
        PaletteCanvas.loadSpawners();
        paletteCanvas.repaint();

        // Adds all frame to the Game class so that they can be referenced from elsewhere later.
        Game.gameFrame = gameFrame;
        Game.paletteFrame = paletteFrame;
        Game.menuFrame = metaMenu.menuFrame;
        Game.controlsFrame = controlsFrame;

        // Adds all canvases to the Game class so that they can be accessed more easily.
        Game.gameCanvas = gameCanvas;
        Game.paletteCanvas = paletteCanvas;

        // Add a reference of the menu frame to the Console class for some reason.
        Console.menuFrame = metaMenu.menuFrame;

        // Main window receives cursor focus.
        gameFrame.requestFocus();
        gameCanvas.setFocusable(true);
        gameCanvas.requestFocusInWindow();

        // Starts main loop.
        Game.tick();
    }
}