package pressjumptospace.main;

import pressjumptospace.entity.EntityHitbox;
import pressjumptospace.entity.LevelObjective;
import pressjumptospace.entity.meta.Enemy;
import pressjumptospace.entity.meta.Item;
import pressjumptospace.entity.player.Controls;
import pressjumptospace.entity.player.Player;
import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.meta.NPC;
import pressjumptospace.level.Chunk;
import pressjumptospace.level.Level;
import pressjumptospace.level.LevelEditor;
import pressjumptospace.menu_old.Console;
import pressjumptospace.render.GameCanvas;
import pressjumptospace.render.PaletteCanvas;
import pressjumptospace.tile.meta.LiquidTile;
import pressjumptospace.util.Physics;
import pressjumptospace.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * All the important game stuff happens here.
 *
 * @author Charlotte Buff
 * @version 1.5
 */

public abstract class Game {
    /**
     * Ticks per second.
     * Speed of the main loop.
     */
    public static final int TPS = 30;

    /**
     * For how many ticks the game has been running.
     */
    public static int age = 0;

    /**
     * Whether the main loop is running.
     */
    public static boolean running = true;

    /**
     * Whether the game is paused.
     * Currently not implemented.
     */
    public static boolean paused = false;    // yeah, as if

    /**
     * Current game mode.
     * 0 = editing; 1 = playing.
     */
    public static byte gameMode = 0;

    /**
     * Current menu status.
     * 0 = main menu visible.
     */
    public static byte menuMode = 0;

    /**
     * Width of the playing field.
     */
    public static final int LEVEL_WIDTH = 896;

    /**
     * Height of the playing field.
     */
    public static final int LEVEL_HEIGHT = 512;

    /**
     * Main window.
     */
    public static JFrame gameFrame;

    /**
     * Palette window.
     */
    public static JFrame paletteFrame;

    /**
     * Menu window.
     */
    public static JFrame menuFrame;

    /**
     * Help window.
     */
    public static JFrame controlsFrame;

    /**
     * Main canvas.
     */
    public static GameCanvas gameCanvas;

    /**
     * Palette canvas.
     */
    public static PaletteCanvas paletteCanvas;

    /**
     * Initiates game logic and kickstarts the main loop.
     * Main loop is executed 30 times per second and calculates all entities' actions and interactions.
     * Playing field is rerendered at the end of every loop cycle.
     *
     * @throws InterruptedException I don't know.
     * @throws InstantiationException I don't know.
     * @throws IllegalAccessException I don't know.
     * @throws ClassNotFoundException I don't know.
     */
    public static void tick() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // The player is always the first entity in the list.
        Level.entities.add(Controls.player);

        // Initiating canvas stuff.
        Graphics2D gPalette = (Graphics2D) paletteCanvas.getGraphics();
        Image bufferImagePalette = (BufferedImage) paletteCanvas.createImage(paletteCanvas.objWidth, paletteCanvas.objHeight);
        Graphics bufferGraphicsPalette = bufferImagePalette.getGraphics();

        Graphics2D gGame = (Graphics2D) gameCanvas.getGraphics();
        Image bufferImageGame = (BufferedImage) gameCanvas.createImage(gameCanvas.getWidth(), gameCanvas.getHeight());
        Graphics bufferGraphicsGame = bufferImageGame.getGraphics();

        // Main loop.
        while (Game.running) {
            // Internal cursor object is updated to the current mouse position.
            gameCanvas.cursor = gameCanvas.getMousePosition();

            // If the game is in editing mode and the mouse cursor is within the bounds of the main window...
            if (Game.gameMode == 0 && gameCanvas.cursor != null) {
                // editing mode
                if (LevelEditor.selectedTile != null) {
                    // If a tile has been selected from the palette, an image of it is rendered stuck to the cursor.
                    // Image position is rounded to the next lowest multiple of 16 to make it align with the grid.
                    LevelEditor.selectedTile.sprite.x = Util.roundToN(gameCanvas.cursor.x, 16);
                    LevelEditor.selectedTile.sprite.y = Util.roundToN(gameCanvas.cursor.y, 16);
                }
                if (LevelEditor.selectedSpawner != null) {
                    // If a spawner has been selected from the palette, an image of it is rendered stuck to the cursor.
                    // Image position is rounded to the next lowest multiple of 16 to make it align with the grid.
                    LevelEditor.selectedSpawner.sprite.x = (gameCanvas.cursor.x / 16) * 16;
                    LevelEditor.selectedSpawner.sprite.y = (gameCanvas.cursor.y / 16) * 16;
                }

                // If the left mouse button is pressed...
                if (LevelEditor.pressed) {
                    if (LevelEditor.eraser) {
                        // If the eraser is selected, delete tile and spawner at the current mouse position.
                        Level.removeTile(gameCanvas.cursor.x, gameCanvas.cursor.y);
                        Level.removeSpawner(gameCanvas.cursor.x, gameCanvas.cursor.y);
                    } else if (LevelEditor.spawnpoint) {
                        // If the player spawnpoint is selected, place it at the current mouse position.
                        Level.addSpawnpoint((gameCanvas.cursor.x / 16) * 16, (gameCanvas.cursor.y / 16) * 16);
                    } else {
                        if (LevelEditor.mode == 0 && LevelEditor.selectedTile != null) {
                            // If the palette is currently on the 'tiles' page,
                            // and if a tile has been selected by the player, add this tile at the current mouse position.
                            Level.addTile(gameCanvas.cursor.x, gameCanvas.cursor.y, LevelEditor.selectedTile.id);
                        }
                        else if (LevelEditor.mode == 1 && LevelEditor.selectedSpawner != null) {
                            // If the palette is currently on the 'spawner' page,
                            // and if a spawner has been selected by the player, add this spawner at the current mouse position.
                            Level.addSpawner(gameCanvas.cursor.x, gameCanvas.cursor.y, LevelEditor.selectedSpawner.type);
                        }
                    }
                }
            }
            // If the game is in playing mode...
            else if (Game.gameMode == 1) {
                // playing mode

                // For all entities that are currently active on the playing field...
                for (int i = 0; i < Level.entities.size(); i++) {
                    // Create a reference variable so that it is easier to use the currently selected entity.
                    Entity entity = Level.entities.get(i);

                    // If the entity is completely below the lower border of the canvas, kill it.
                    if (entity.y > Game.LEVEL_HEIGHT + 16) {
                        entity.kill();
                    }

                    // Synchronize the entity's hitbox with its current position.
                    entity.updateHitboxLocation();

                    // If the entity is an NPC, run its AI routine.
                    if (entity instanceof NPC) {
                        ((NPC) entity).ai();
                    }

                    // If the entity is affected by gravity...
                    // ** If there is solid ground directly beneath the entity, set its vertical velocity to 0, making it stop.
                    // ** If there is no solid ground directly beneath the entity, increase its vertical velocity, making it fall.
                    if (entity.willFall) {
                        entity.velY = (entity.getCollision('d') == 0) ? 0f : entity.velY + Physics.gravity;
                    }

                    // If the player is pressing the jump button, and they are either on solid ground or within a liquid, make them jump.
                    if (Controls.player.triesJumping && (Controls.player.getCollision('d') == 0 || Controls.player.isInTile(LiquidTile.class))) {
                        Controls.player.jump(-8f);
                    }

                    // If the player is pressing the walk-left button and they aren't pressing against a solid wall to the left, make them walk.
                    // The walking direction is determined automatically by the state of the player.dir attribute.
                    if (Controls.player.triesWalking && Controls.player.dir == 3 && Controls.player.getCollision('l') > 0) {
                        Controls.player.walk(3f);
                    }
                    // If the player is pressing the walk-right button and they aren't pressing against a solid wall to the right, make them walk.
                    else if (Controls.player.triesWalking && Controls.player.dir == 1 && Controls.player.getCollision('r') > 0) {
                        Controls.player.walk(3f);
                    }
                    // If the player isn't pressing either walk button, make them stop horizontally.
                    else {
                        Controls.player.walk(0f);
                    }

                    // If the entity is moving upwards and it is pressing directly against a ceiling, set its vertical velocity to 0.
                    if (((EntityHitbox) entity.hitbox).getCollision('u') == 0 && entity.velY < 0) {
                        entity.velY = 0f;
                    }

                    // If the entity is stack in a liquid, reduce its velocity.
                    // Speed reduction is determined by the average of all tiles it currently intersects.
                    if (entity.isInTile(LiquidTile.class)) {
                        entity.velX *= entity.getAverageViscosity();
                        entity.velY *= entity.getAverageViscosity();
                    }

                    // After all of these speed calculations, the entity's position is recalculated.
                    // For every direction, the distance an entity moves within a tick is determined by its velocity and its distance to the nearest surface.
                    // If the velocity value is smaller than the distance, it moves the full way set by the velocity.
                    // If the distance to the nearest surface is smaller than the velocity, it only moves up to that surface and not further.
                    // Collision direction for the horizontal axis is determined by the entity's own walking direction.
                    // Collision direction for the vertical axis is always 'down' for some reason.
                    // I'm not really sure how that makes sense. I have to investigate later.
                    entity.x = Math.round(entity.x + Math.min(entity.velX, entity.getCollision(entity.dirToChar())));
                    entity.y = Math.round(entity.y + Math.min(entity.velY, entity.getCollision('d')));

                    // The entity's sprite is updated to its new location.
                    entity.updateSpriteLocation();

                    // If the entity is still under the effect of damage cooldown, its timer is reduced by 1.
                    entity.tickDamageCooldown();

                    // If the entity is an item and the player touches it, the player collects the item.
                    if (entity instanceof Item && entity.isInEntity(Player.class)) {
                        Controls.player.collect((Item) entity);
                    }

                    // If the entity no longer has damage cooldown, it is damaged, should there be anything damaging it.
                    if (entity.damageCooldown == 0) {
                        entity.hurt(entity.getTotalHurts());

                        // If an enemy is vulnerable from the top and the player falls or jumps onto the enemy from above, the enemy takes damage.
                        // This also makes the player automatically jump higher than normal.
                        if (entity instanceof Enemy && entity.vulnerable[0] && (Controls.player.containsCoordinates(entity.pos('x'), entity.pos('y') - 1) || Controls.player.containsCoordinates(entity.pos('x') + entity.hitbox.width - 1, entity.pos('y') - 1)) && Controls.player.velY <= 0) {
                            Controls.player.dealDamage(entity, Controls.player.hurts[2]);

                            Controls.player.jump(-10f);
                        }
                    }

                    // If an entity has no health left, it is removed from the game.
                    if (entity.health <= 0) {
                        Level.entities.remove(i);
                    }
                }

                // If the player touches the level objective, they win the game.
                if (Controls.player.isInEntity(LevelObjective.class)) {
                    Controls.player.win();
                }

                // If the player has no health left, they lose the game.
                if (Controls.player.health <= 0) {
                    Controls.player.lose();
                }
            }

            // After everything has been calculated, a new frame is rendered.
            renderGame(bufferGraphicsGame, bufferImageGame, gGame);

            // If the game is in editing mode, the palette also renders a new frame.
            // The palette window is hidden in playing mode, so no ressources are wasted.
            if (gameMode == 0) {
                renderPalette(bufferGraphicsPalette, bufferImagePalette, gPalette);
            }

            // Age value is increased by 1.
            Game.age++;

            // The program waits for 33 milliseconds before the main loop is executed again.
            Thread.sleep(1000 / Game.TPS);
        }
    }

    /**
     * Renders the playing field.
     * Don't ask me how this works.
     *
     * @param bufferGraphics I don't know.
     * @param bufferImage I don't know.
     * @param g I don't know.
     */
    public static void renderGame(Graphics bufferGraphics, Image bufferImage, Graphics2D g) {
        // The canvas is cleared by replacing every with black.
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Render instruction is passed on to the canvas, or something.
        gameCanvas.render(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, null);

        // gameCanvas.repaint();
    }

    /**
     * Renders the palette.
     * Don't ask me how this works.
     *
     * @param bufferGraphics I don't know.
     * @param bufferImage I don't know.
     * @param g I don't know.
     */
    public static void renderPalette(Graphics bufferGraphics, Image bufferImage, Graphics2D g) {
        // Same as above.
        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, paletteCanvas.objWidth, paletteCanvas.objHeight);

        paletteCanvas.render(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, null);

        // paletteCanvas.repaint();
    }

    /**
     * Switches between editing mode (0) and playing mode (1).
     */
    public static void toggleMode() {
        if (Game.gameMode == 0) {
            // Changing from editor mode to play mode.
            Game.changeMode((byte) 1);
        } else {
            // Changing from play mode to editor mode.
            Game.changeMode((byte) 0);
        }
    }

    /**
     * Changes the game mode to a specific value.
     *
     * @param mode_ 0 = editing; 1 = playing.
     */
    public static void changeMode(byte mode_) {
        Game.gameMode = mode_;

        switch (Game.gameMode) {
            case 0:
                // Changing to editing mode.
                // The palette window becomes visible again.
                Game.paletteFrame.setVisible(true);

                // All entities are removed from the playing field.
                Level.entities.clear();
                break;
            case 1:
                // Changing to playing mode.
                // The player character is reset to its initial properties, i.e. full health.
                Controls.player.reset();

                // All spawners on the playing field spawn one entity at their position.
                for (int i = 0; i < Level.totalChunks; i++) {
                    // If there is something in this chunk...
                    if (Level.getChunk(i) != null) {
                        for (int j = 0; j < Chunk.totalSpawners; j++) {
                            // If there is a spawner at this position...
                            if (Level.getChunk(i).getSpawner(j) != null) {
                                // Create an entity.
                                Level.getChunk(i).getSpawner(j).spawn();
                            }
                        }
                    }
                }

                Console.clear();

                // The palette window is hidden because it is not needed.
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