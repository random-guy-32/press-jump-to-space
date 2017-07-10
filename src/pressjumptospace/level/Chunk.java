package pressjumptospace.level;

import pressjumptospace.entity.EntitySpawner;
import pressjumptospace.entity.meta.Entity;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.util.Util;

public class Chunk {
    public Chunk() {
        tilesN = 0;
        spawnersN = 0;
    }

    /*
    public Chunk(Tile[] tiles_) {
        tiles = tiles_;
        tilesN = 0;
        spawnersN = 0;
    }
    */

    /*
    TODO: Make tilesN and spawnersN work correctly even when using a constructor.
     */

    public Tile[] tiles = new Tile[Chunk.totalTiles];
    public int tilesN = 0;

    public EntitySpawner[] spawners = new EntitySpawner[Chunk.totalSpawners];
    public int spawnersN = 0;

    public static final int totalTiles = 64;
    public static final int totalSpawners = 64;

    public Tile getTile(int i) {
        if (i < 0 || i >= Chunk.totalTiles) {
            return null;
        }
        else {
            return this.tiles[i];
        }
    }
    public Tile getTile(int x, int y) {
        if (y * 8 + x < 0 || y * 8 + x >= Chunk.totalTiles) {
            return null;
        }
        else {
            return this.getTile(y * 8 + x);
        }
    }
    public void addTile(int i, Tile tile) {
        this.addTile(i % 8, i / 8, tile);
    }
    public void addTile(int x, int y, Tile tile) {
        if (this.tiles[y * 8 + x] == null) {
            this.tiles[y * 8 + x] = tile;
            this.tilesN++;
        }
    }
    public void removeTile(int i) {
        this.removeTile(i % 8, i / 8);
    }
    public void removeTile(int x, int y) {
        if (this.getTile(x, y) != null) {
            this.tiles[y * 8 + x] = null;
            this.tilesN--;
        }
    }

    public EntitySpawner getSpawner(int i) {
        return this.getSpawner(i % 8, i / 8);
    }
    public EntitySpawner getSpawner(int x, int y) {
        if (y * 8 + x < 0 || y * 8 + x >= 64) {
            return null;
        }
        return this.spawners[y * 8 + x];
    }
    public void addSpawner(int i, EntitySpawner spawner) {
        this.addSpawner(i % 8, i / 8, spawner);
    }
    public void addSpawner(int x, int y, EntitySpawner spawner) {
        if (this.spawners[y * 8 + x] == null) {
            this.spawners[y * 8 + x] = spawner;
            this.spawnersN++;
        }
    }
    public void removeSpawner(int i) {
        this.removeSpawner(i % 8, i / 8);
    }
    public void removeSpawner(int x, int y) {
        if (this.getSpawner(x, y) != null) {
            this.spawners[y * 8 + x] = null;
            this.spawnersN--;
        }
    }

    public short getTileID(int i) {
        return (this.getTile(i) == null) ? 0 : this.getTile(i).tileID;
    }
    public short getTileID(int x, int y) {
        return (this.getTile(x, y) == null) ? 0 : this.getTile(x, y).tileID;
    }
}