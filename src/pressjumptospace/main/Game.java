package pressjumptospace.main;

import com.sun.javafx.scene.control.skin.ColorPalette;
import pressjumptospace.entity.EntityHitbox;
import pressjumptospace.entity.LevelObjective;
import pressjumptospace.entity.meta.Enemy;
import pressjumptospace.entity.meta.Item;
import pressjumptospace.entity.player.Controls;
import pressjumptospace.entity.player.Player;
import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.meta.NPC;
import pressjumptospace.level.Level;
import pressjumptospace.level.LevelEditor;
import pressjumptospace.menu.Console;
import pressjumptospace.render.GameCanvas;
import pressjumptospace.render.PaletteCanvas;
import pressjumptospace.tile.meta.LiquidTile;
import pressjumptospace.util.Physics;
import pressjumptospace.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Game {
    public static final int TPS = 30;
    public static int age = 0;
    public static boolean running = true;
    public static boolean paused = false;    // yeah, as if
    public static byte mode = 0;

    public static final int OBJWIDTH = 896;
    public static final int OBJHEIGHT = 512;

    public static JFrame gameFrame;
    public static JFrame paletteFrame;
    public static JFrame menuFrame;
    public static JFrame controlsFrame;

    public static GameCanvas gameCanvas;
    public static PaletteCanvas paletteCanvas;

    public static void tick() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Player player = Controls.player;
        Level.entities.add(player);

        // initiating canvas stuff
        Graphics2D gPalette = (Graphics2D) paletteCanvas.getGraphics();
        Image bufferImagePalette = (BufferedImage) paletteCanvas.createImage(paletteCanvas.objWidth, paletteCanvas.objHeight);
        Graphics bufferGraphicsPalette = bufferImagePalette.getGraphics();

        Graphics2D gGame = (Graphics2D) gameCanvas.getGraphics();
        Image bufferImageGame = (BufferedImage) gameCanvas.createImage(gameCanvas.getWidth(), gameCanvas.getHeight());
        Graphics bufferGraphicsGame = bufferImageGame.getGraphics();

        // main loop
        while (Game.running) {
            // Util.log("TICK.");

            gameCanvas.cursor = gameCanvas.getMousePosition();
            if (Game.mode == 0 && gameCanvas.cursor != null) {
                // editing mode
                if (LevelEditor.selectedTile != null) {
                    LevelEditor.selectedTile.sprite.x = Util.roundToN(gameCanvas.cursor.x, 16);
                    LevelEditor.selectedTile.sprite.y = Util.roundToN(gameCanvas.cursor.y, 16);
                }
                if (LevelEditor.selectedSpawner != null) {
                    LevelEditor.selectedSpawner.sprite.x = (gameCanvas.cursor.x / 16) * 16;
                    LevelEditor.selectedSpawner.sprite.y = (gameCanvas.cursor.y / 16) * 16;
                }

                if (LevelEditor.pressed) {
                    if (LevelEditor.eraser) {
                        Level.removeTile(gameCanvas.cursor.x, gameCanvas.cursor.y);
                        Level.removeSpawner(gameCanvas.cursor.x, gameCanvas.cursor.y);
                    } else if (LevelEditor.spawnpoint) {
                        Level.addSpawnpoint((gameCanvas.cursor.x / 16) * 16, (gameCanvas.cursor.y / 16) * 16);
                    } else {
                        if (LevelEditor.mode == 0 && LevelEditor.selectedTile != null) {
                            Level.addTile(gameCanvas.cursor.x, gameCanvas.cursor.y, LevelEditor.selectedTile.id);
                        }
                        else if (LevelEditor.mode == 1 && LevelEditor.selectedSpawner != null) {
                            Level.addSpawner(gameCanvas.cursor.x, gameCanvas.cursor.y, LevelEditor.selectedSpawner.type);
                        }
                    }
                }
            }
            else if (Game.mode == 1) {
                // playing mode
                for (int i = 0; i < Level.entities.size(); i++) {
                    Entity entity = Level.entities.get(i);

                    if (entity.y > Game.OBJHEIGHT + 16) {
                        entity.kill();
                    }

                    /*
                    if (entity.health <= 0) {
                        Level.entities.remove(i);
                    }
                    */

                    entity.updateHitboxLocation();

                    if (entity instanceof NPC) {
                        ((NPC) entity).ai();
                    }

                    if (entity.willFall) {
                        entity.velY = (entity.getCollision('d') == 0) ? 0f : entity.velY + Physics.gravity;
                    }

                    if (Controls.player.triesJumping && (Controls.player.getCollision('d') == 0 || Controls.player.isInTile(LiquidTile.class))) {
                        Controls.player.jump(-8f);
                    }
                    if (Controls.player.triesWalking && Controls.player.dir == 3 && Controls.player.getCollision('l') > 0) {
                        Controls.player.walk(3f);
                    } else if (Controls.player.triesWalking && Controls.player.dir == 1 && Controls.player.getCollision('r') > 0) {
                        Controls.player.walk(3f);
                    } else {
                        Controls.player.walk(0f);
                    }

                    if (((EntityHitbox) entity.hitbox).getCollision('u') == 0 && entity.velY < 0) {
                        entity.velY = 0f;
                    }

                    if (entity.isInTile(LiquidTile.class)) {
                        entity.velX *= entity.getAverageViscosity();
                        entity.velY *= entity.getAverageViscosity();
                    }

                    entity.x = Math.round(entity.x + Math.min(entity.velX, entity.getCollision(entity.dirToChar())));
                    entity.y = Math.round(entity.y + Math.min(entity.velY, entity.getCollision('d')));

                    entity.updateSpriteLocation();

                    entity.tickDamageCooldown();

                    if (entity instanceof Item && entity.isInEntity(Player.class)) {
                        Controls.player.collect((Item) entity);
                    }

                    if (entity.damageCooldown == 0) {
                        entity.hurt(entity.getTotalHurts());

                        if (entity instanceof Enemy && entity.vulnerable[0] && (Controls.player.containsCoordinates(entity.pos('x'), entity.pos('y') - 1) || Controls.player.containsCoordinates(entity.pos('x') + entity.hitbox.width - 1, entity.pos('y') - 1)) && Controls.player.velY <= 0) {
                            Controls.player.dealDamage(entity, Controls.player.hurts[2]);

                            Controls.player.jump(-10f);
                        }
                    }

                    if (entity.health <= 0) {
                        // Util.log("Removed entity " + Level.entities.get(i));
                        Level.entities.remove(i);
                    }
                }

                if (Controls.player.isInEntity(LevelObjective.class)) {
                    Controls.player.win();
                }
                if (Controls.player.health <= 0) {
                    Controls.player.lose();
                }
            }

            //	System.out.println("POSX:" + Controls.player.x + ", POSY:" + Controls.player.y);

            renderGame(bufferGraphicsGame, bufferImageGame, gGame);
            if (mode == 0) {
                renderPalette(bufferGraphicsPalette, bufferImagePalette, gPalette);
            }

            Game.age++;
            Thread.sleep(1000 / Game.TPS);
        }
    }

    public static void renderGame(Graphics bufferGraphics, Image bufferImage, Graphics2D g) {
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        gameCanvas.render(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, null);

        // gameCanvas.repaint();
    }
    public static void renderPalette(Graphics bufferGraphics, Image bufferImage, Graphics2D g) {
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, paletteCanvas.objWidth, paletteCanvas.objHeight);

        paletteCanvas.render(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, null);

        // paletteCanvas.repaint();
    }

    public static void toggleMode() {
        if (Game.mode == 0) {
            // changing from editor mode to play mode
            Game.changeMode((byte) 1);
        } else {
            // changing from play mode to editor mode
            Game.changeMode((byte) 0);
        }
    }

    public static void changeMode(byte mode_) {
        Game.mode = mode_;

        switch (Game.mode) {
            case 0:
                // changing to editing mode
                Game.paletteFrame.setVisible(true);

                Level.entities.clear();
                break;
            case 1:
                // changing to playing mode
                Controls.player.reset();

                for (int i = 0; i < Level.totalChunks; i++) {
                    if (Level.getChunk(i) != null) {
                        for (int j = 0; j < Level.getChunk(i).totalSpawners; j++) {
                            if (Level.getChunk(i).getSpawner(j) != null) {
                                Level.getChunk(i).getSpawner(j).spawn();
                            }
                        }
                    }
                }

                Console.clear();

                Game.paletteFrame.setVisible(false);
                break;
            default:
                Util.err("Unknown game mode.");
        }

        Game.gameFrame.requestFocus();
        Game.gameCanvas.setFocusable(true);
        Game.gameCanvas.requestFocusInWindow();
    }
}