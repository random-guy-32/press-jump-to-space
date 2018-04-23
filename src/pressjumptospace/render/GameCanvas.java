package pressjumptospace.render;

import pressjumptospace.entity.player.Player;
import pressjumptospace.level.Level;
import pressjumptospace.level.LevelEditor;
import pressjumptospace.level.Spawnpoint;
import pressjumptospace.entity.player.Controls;
import pressjumptospace.main.Game;
import pressjumptospace.menu.GraphicalInterfaceComponent;
import pressjumptospace.menu.GraphicalInterfaceContainer;
import pressjumptospace.util.SpriteManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * Yes.
 *
 * @author Charlotte Buff
 * @version 1.5
 */

public class GameCanvas extends Canvas {
    public GameCanvas(Player player_) throws IOException {
        super();
        addMouseListener(prepareMouseInput());
        addKeyListener(new Controls(player_));
        setFocusable(true);

        guiRoot = new GraphicalInterfaceContainer(0, 0, getWidth(), getHeight());
        guiElements = new ArrayList<>();
        guiElements.add(guiRoot);
    }

    public List<GraphicalInterfaceComponent> guiElements;
    public GraphicalInterfaceContainer guiRoot;

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.renderLevel(g);
    }

    public void renderLevel(Graphics g) {
        this.renderTiles(g);
        this.renderEntities(g);
        this.renderSpawnpoint(g);
        this.renderSpawners(g);
        this.renderTileEntities(g);
    }

    public void renderTiles(Graphics g) {
        // TILES
        for (int i = 0; i < Level.chunks.length; i++) {
            if (Level.chunks[i] != null && Level.chunks[i].tilesN != 0) {
                for (int j = 0; j < Level.chunks[i].tiles.length; j++) {
                    if (Level.chunks[i].tiles[j] != null) {
                        Level.getChunk(i).tiles[j].sprite.render(g, ((i % (this.getWidth() / 128)) * 128) + ((j % 8) * 16), ((i / (this.getWidth() / 128)) * 128) + ((j / 8) * 16));
                    }
                }
            }
        }
    }
    public void renderEntities(Graphics g) {
        // ENTITIES
        for (int i = 0; i < Level.entities.size(); i++) {
            // fghjkl
            if (Game.gameMode == 1) {
                Level.entities.get(i).render(g);
            }
        }
    }
    public void renderSpawnpoint(Graphics g) {
        // SPAWNPOINT
        if (Game.gameMode == 0 && Spawnpoint.set) {
            g.drawImage(SpriteManager.loadImage(Spawnpoint.sprite), Spawnpoint.x, Spawnpoint.y, null);
        }
    }
    public void renderSpawners(Graphics g) {
        // SPAWNERS
        if (Game.gameMode == 0) {
            for (int i = 0; i < Level.totalChunks; i++) {
                if (Level.getChunk(i) != null && Level.getChunk(i).spawnersN > 0) {
                    for (int j = 0; j < Level.getChunk(i).totalSpawners; j++) {
                        if (Level.getChunk(i).spawners[j] != null) {
                            Level.getChunk(i).spawners[j].render(g);
                        }
                    }
                }
            }
        }
    }
    public void renderTileEntities(Graphics g) {
        // TILE ENTITIES
        if (Game.gameMode == 0 && this.cursor != null) {
            if (LevelEditor.eraser) {
                g.drawImage(SpriteManager.loadImage("gui/eraser.png"), this.cursor.x, this.cursor.y, null);
            }
            else if (LevelEditor.spawnpoint) {
                g.drawImage(SpriteManager.loadImage(Spawnpoint.sprite), (this.cursor.x / 16) * 16, (this.cursor.y / 16) * 16, null);
            }
            else {
                if (LevelEditor.mode == 0 && LevelEditor.selectedTile != null) {
                    g.drawImage(SpriteManager.loadImage(LevelEditor.selectedTile.sprite.src), LevelEditor.selectedTile.sprite.x, LevelEditor.selectedTile.sprite.y, null);
                }
                else if (LevelEditor.mode == 1 && LevelEditor.selectedSpawner != null) {
                    g.drawImage(SpriteManager.loadImage(LevelEditor.selectedSpawner.sprite.src), LevelEditor.selectedSpawner.sprite.x, LevelEditor.selectedSpawner.sprite.y, null);
                }
            }
        }
    }

    public MouseAdapter prepareMouseInput() {
        return new MouseAdapter() {
            // this is not acceptable
			/*
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					int x = e.getX(), y = e.getY();
					
					if (LevelEditor.eraser) {
						Level.removeTile(x, y);
					}
					else if (LevelEditor.selectedTile != null) {
						Level.addTile(x, y, LevelEditor.selectedTile.tileID);
					}
				}
			}
			*/
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // int x = e.getX(), y = e.getY();

                    LevelEditor.pressed = true;
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // int x = e.getX(), y = e.getY();

                    LevelEditor.pressed = false;
                }
            }
        };
    }
}