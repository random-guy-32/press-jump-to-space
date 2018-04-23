package pressjumptospace.level;

import pressjumptospace.entity.EntitySpawner;
import pressjumptospace.entity.player.Controls;
import pressjumptospace.main.Game;
import pressjumptospace.tile.meta.Tile;

public class Region {
    public Region(int x_, int y_) {
        x = x_;
        y = y_;
        chunks = new Chunk[Region.totalChunks];
    }

    public static final int totalChunks = (Game.LEVEL_WIDTH / 128) * (Game.LEVEL_HEIGHT / 128);
    public Chunk[] chunks;
    public int x;
    public int y;

    public Tile getTile(int x, int y) {
        return this.getChunk(x / 128, y / 128).getTile((x / 16) % 8, (y / 16) % 8);
    }
    public void addTile(int x, int y, short tileID) {
        this.getChunk(x / 128, y / 128).addTile((x / 16) % 8, (y / 16) % 8, Level.tileset.get(tileID));
    }
    public void addSpawner(int x, int y, Class type) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        this.getChunk(x / 128, y / 128).addSpawner((x / 16) % 8, (y / 16) % 8, new EntitySpawner(type.getName(), (x / 16) * 16, (y / 16) * 16));
    }
    public void removeTile(int x, int y) {
        this.getChunk(x / 128, y / 128).removeTile((x / 16) % 8, (y / 16) % 8);
    }
    public void removeSpawner(int x, int y) {
        this.getChunk(x / 128, y / 128).removeSpawner((x / 16) % 8, (y / 16) % 8);
    }
    public Chunk getChunk(int i) {
        if (i < 0 || i >= Region.totalChunks) {
            return DummyChunk.c;
        }
        else if (this.chunks[i] == null) {
            this.chunks[i] = new Chunk();
        }
        return this.chunks[i];
    }
    public Chunk getChunk(int x, int y) {
        return this.getChunk(y * (Game.LEVEL_WIDTH / 128) + x);
    }

    public void initiateChunks() {
        for (int i = 0; i < Region.totalChunks; i++) {
            this.chunks[i] = new Chunk();
        }
    }

    public void addSpawnpoint(int x, int y) {
        Spawnpoint.x = x;
        Spawnpoint.y = y;
        Spawnpoint.set = true;

        Controls.player.x = x;
        Controls.player.y = y;
    }
}
