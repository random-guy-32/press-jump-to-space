package pressjumptospace.render;

import pressjumptospace.entity.*;
import pressjumptospace.entity.meta.CursorBoundObject;
import pressjumptospace.level.Level;
import pressjumptospace.level.LevelEditor;
import pressjumptospace.level.Spawnpoint;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.util.SpriteManager;
import pressjumptospace.util.Util;

import java.awt.*;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class  PaletteCanvas extends Canvas {
    public PaletteCanvas(int objWidth_, int objHeight_) throws IOException {
        super();
        objWidth = objWidth_;
        objHeight = objHeight_;
        addMouseListener(prepareMouseInput());
    }

    public int objWidth;
    public int objHeight;
    public static List<Tile> tiles = new ArrayList<Tile>();
    public static List<EntitySpawner> spawners = new ArrayList<EntitySpawner>();

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (LevelEditor.mode == 0) {
            for (int i = 0; i < PaletteCanvas.tiles.size(); i++) {
                PaletteCanvas.tiles.get(i).render(g, (i / (this.objWidth / 16)) * 16, (i % (this.objWidth / 16)) * 16);
            }
        }
        else {
            for (int i = 0; i < PaletteCanvas.spawners.size(); i++) {
                PaletteCanvas.spawners.get(i).render(g, (i / (this.objWidth / 16)) * 16, (i % (this.objWidth / 16)) * 16);
            }
        }

        // eraser
        g.drawImage(SpriteManager.loadImage(Eraser.sprite), this.objWidth - 16, this.objHeight - 16, null);
        g.drawImage(SpriteManager.loadImage(Spawnpoint.sprite), this.objWidth - 16, this.objHeight - 32, null);
        g.drawImage(SpriteManager.loadImage(PaletteSwitchButton.sprite), this.objWidth - 16, this.objHeight - 48, null);
    }
    public static void loadTiles() {
        for (int i = 1; i < Level.tileset.size(); i++) {
            PaletteCanvas.tiles.add(Level.tileset.get(i));
        }
    }
    public static void loadSpawners() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (int i = 1; i < Level.entityset.size(); i++) {
            PaletteCanvas.spawners.add(new EntitySpawner(Level.entityset.get(i).getName()));
        }
    }
    public CursorBoundTile getTile(int x, int y) {
        int i = (x / 16) * (this.objHeight / 16) + (y / 16);

        if (i == (this.objWidth / 16) * (this.objHeight / 16) - 1) {
            // eraser
            LevelEditor.eraser = true;
            LevelEditor.spawnpoint = false;
            return new CursorBoundTile(0, 0, (short) -16);
        }
        else if (i == (this.objWidth / 16) * (this.objHeight / 16) - 2) {
            // spawnpoint
            LevelEditor.spawnpoint = true;
            LevelEditor.eraser = false;
            return new CursorBoundTile(0, 0, (short) -16);
        }
        else if (i == (this.objWidth / 16) * (this.objHeight / 16) - 3) {
            // mode switch
            LevelEditor.toggleMode();
            return null;
        }
        else if (i < 0 || i > PaletteCanvas.tiles.size() - 1) {
            return null;
        }
        else {
            LevelEditor.eraser = false;
            LevelEditor.spawnpoint = false;
            return new CursorBoundTile(0, 0, PaletteCanvas.tiles.get(i).tileID);
        }
    }
    public CursorBoundSpawner getSpawner(int x, int y) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        int i = (x / 16) * (this.objHeight / 16) + (y / 16);

        if (i == (this.objWidth / 16) * (this.objHeight / 16) - 1) {
            // eraser
            LevelEditor.eraser = true;
            LevelEditor.spawnpoint = false;
            return new CursorBoundSpawner(0, 0, Eraser.class);
        }
        else if (i == (this.objWidth / 16) * (this.objHeight / 16) - 2) {
            // spawnpoint
            LevelEditor.spawnpoint = true;
            LevelEditor.eraser = false;
            return new CursorBoundSpawner(0, 0, Spawnpoint.class);
        }
        else if (i == (this.objWidth / 16) * (this.objHeight / 16) - 3) {
            // mode switch
            LevelEditor.toggleMode();
            return null;
        }
        else if (i < 0 || i > PaletteCanvas.tiles.size() - 1) {
            return null;
        }
        else {
            LevelEditor.eraser = false;
            LevelEditor.spawnpoint = false;

            return new CursorBoundSpawner(0, 0, PaletteCanvas.spawners.get(i).type);
        }
    }
    public MouseAdapter prepareMouseInput() {
        return new MouseAdapter() {
            // this is not acceptable
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX(), y = e.getY();

                    try {
                        CursorBoundObject selection;

                        if (LevelEditor.mode == 0) {
                            selection = getTile(x, y);

                            if (selection != null) {
                                LevelEditor.selectedTile = (CursorBoundTile) selection;
                            }
                        }
                        else if (LevelEditor.mode == 1) {
                            selection = getSpawner(x, y);

                            if (selection != null) {
                                if (((CursorBoundSpawner) selection).type != Eraser.class && ((CursorBoundSpawner) selection).type != Spawnpoint.class) {
                                    LevelEditor.selectedSpawner = (CursorBoundSpawner) selection;
                                }
                            }
                        }
                    }
                    catch(ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                        Util.err("Couldn't initialize entity spawner.");
                    }
                }
            }
        };
    }
}