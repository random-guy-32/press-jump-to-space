package pressjumptospace.entity.player;

import pressjumptospace.main.Game;
import pressjumptospace.menu.Console;
import pressjumptospace.render.Screen;
import pressjumptospace.util.FileManager;
import pressjumptospace.util.Util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class Controls extends KeyAdapter {
    public static Player player;

    public Controls(Player player) {
        Controls.player = player;
    }

    public void keyPressed(KeyEvent e) {
        // JUMPING
        if (e.getKeyCode() == Controls.JUMP) {
            player.triesJumping = true;
        }
        // WALKING LEFT
        if (e.getKeyCode() == Controls.LEFT) {
            player.dir = 3;
            player.triesWalking = true;
        }
        // WALKING RIGHT
        if (e.getKeyCode() == Controls.RIGHT) {
            player.dir = 1;
            player.triesWalking = true;
        }
        // MODE TOGGLING
        if (e.getKeyCode() == Controls.TOGGLEMODE) {
            Game.toggleMode();
        }
        // SCREEN MOVING
        if (Game.mode == 0) {
            if (e.getKeyCode() == Controls.SCREENUP) {
                Screen.moveScreen('y', -Screen.scrollAmount);
            }
            if (e.getKeyCode() == Controls.SCREENDOWN) {
                Screen.moveScreen('y', Screen.scrollAmount);
            }
            if (e.getKeyCode() == Controls.SCREENLEFT) {
                Screen.moveScreen('x', -Screen.scrollAmount);
            }
            if (e.getKeyCode() == Controls.SCREENRIGHT) {
                Screen.moveScreen('x', Screen.scrollAmount);
            }
        }
        // DEBUG
        if (e.getKeyCode() == Controls.DEBUG) {
            try {
                FileManager.save("newformattest");
            }
            catch(IOException e2) {
                Util.err("Well, there's your problem.");
            }
        }
        if (e.getKeyCode() == Controls.DEBUG2) {
            try {
                FileManager.load("newformattest");
            }
            catch(IOException e2) {
                Util.err("I can't let you do that dave.");
            }
        }
        if (e.getKeyCode() == Controls.DEBUG3) {
            Util.log(FileManager.SAVESLOCATION);
        }
        if (e.getKeyCode() == Controls.DEBUG4) {
            Console.print("TEST TEST");
        }
    }
    public void keyReleased(KeyEvent e) {
        //JUMPING
        if (e.getKeyCode() == Controls.JUMP) {
            player.triesJumping = false;
        }
        // WALKING LEFT
        if (e.getKeyCode() == Controls.LEFT) {
            player.triesWalking = false;
        }
        // WALKING RIGHT
        if (e.getKeyCode() == Controls.RIGHT) {
            player.triesWalking = false;
        }
    }
    /*
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_P) {
            // isDoingStuff = true;
        }
    }
    public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_P) {
            // isDoingStuff = false;
        }
    }
    */
    public static int UP = KeyEvent.VK_W;
    public static int DOWN = KeyEvent.VK_S;
    public static int LEFT = KeyEvent.VK_A;
    public static int RIGHT = KeyEvent.VK_D;
    public static int JUMP = KeyEvent.VK_SPACE;
    public static int TOGGLEMODE = KeyEvent.VK_ENTER;

    public static int SCREENUP = KeyEvent.VK_UP;
    public static int SCREENDOWN = KeyEvent.VK_DOWN;
    public static int SCREENLEFT = KeyEvent.VK_LEFT;
    public static int SCREENRIGHT = KeyEvent.VK_RIGHT;

    public static int DEBUG = KeyEvent.VK_0;
    public static int DEBUG2 = KeyEvent.VK_1;
    public static int DEBUG3 = KeyEvent.VK_2;
    public static int DEBUG4 = KeyEvent.VK_3;
    public static int DEBUG5 = KeyEvent.VK_4;
    public static int DEBUG6 = KeyEvent.VK_5;
    public static int DEBUG7 = KeyEvent.VK_6;
    public static int DEBUG8 = KeyEvent.VK_7;
    public static int DEBUG9 = KeyEvent.VK_8;
    public static int DEBUG10 = KeyEvent.VK_9;
}