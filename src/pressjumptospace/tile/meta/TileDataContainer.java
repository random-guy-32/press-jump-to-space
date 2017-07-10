package pressjumptospace.tile.meta;

import pressjumptospace.level.Level;

import java.util.HashMap;

public abstract class TileDataContainer {
    public TileDataContainer(Tile tile_, int x_, int y_, int tileX_, int tileY_) {
        x = x_;
        y = y_;
        tileX = tileX_;
        tileY = tileY_;
        tile = tile_;
        age = 0;
        data = new HashMap<>();

        Level.tileDataContainers.add(this);
    }
    public TileDataContainer(Tile tile_, int x_, int y_) {
        x = x_;
        y = y_;
        tileX = x_ / 16;
        tileY = y_ / 16;
        tile = tile_;
        age = 0;
        data = new HashMap<>();

        Level.tileDataContainers.add(this);
    }

    public int x;
    public int y;
    public int tileX;
    public int tileY;
    public Tile tile;
    public int age;
    public HashMap<String, String> data;

    public abstract void action();
}
