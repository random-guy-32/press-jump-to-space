package pressjumptospace.level;

import pressjumptospace.entity.*;
import pressjumptospace.entity.item.Battery;
import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.npc.MotionlessBlob;
import pressjumptospace.entity.npc.RollingPlatform;
import pressjumptospace.entity.npc.SimpleMook;
import pressjumptospace.entity.player.Controls;
import pressjumptospace.entity.projectile.SmallBullet;
import pressjumptospace.main.Game;
import pressjumptospace.tile.barrier.DownOneWayBarrier;
import pressjumptospace.tile.barrier.LeftOneWayBarrier;
import pressjumptospace.tile.barrier.RightOneWayBarrier;
import pressjumptospace.tile.barrier.UpOneWayBarrier;
import pressjumptospace.tile.basic.*;
import pressjumptospace.tile.liquid.Acid;
import pressjumptospace.tile.liquid.AcidSurface;
import pressjumptospace.tile.liquid.BlueGoo;
import pressjumptospace.tile.liquid.GreenGoo;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.tile.meta.tile_data.TileDataContainer;
import pressjumptospace.tile.meta.tile_data.TileWithDataContainer;
import pressjumptospace.tile.misc.DecorativeMetalTile;
import pressjumptospace.tile.misc.DecorativeMetalTile2;
import pressjumptospace.tile.misc.Pedestal;
import pressjumptospace.tile.spike.*;
import pressjumptospace.tile.turret.HorizontalSmallBulletTurret;

import java.util.ArrayList;
import java.util.List;

public class Level {
    // Java taught me that your program becomes better when you have as many unnecessary classes as possible
    public static List<Entity> entities = new ArrayList<Entity>();
    public static List<TileDataContainer> tileDataContainers = new ArrayList<TileDataContainer>();
    public static List<Tile> tileset = new ArrayList<Tile>();
    public static List<Class> entityset = new ArrayList<Class>();

    public static List <Region> regions = new ArrayList<Region>();

    public static final int totalChunks = (Game.OBJWIDTH / 128) * (Game.OBJHEIGHT / 128);
    public static Chunk[] chunks = new Chunk[Level.totalChunks];

    public static Tile getTile(int x, int y) {
        return Level.getChunk(x / 128, y / 128).getTile((x / 16) % 8, (y / 16) % 8);
    }
    public static void addTile(int x, int y, short tileID) {
        Tile tile = Tile.byID(tileID);

        Level.getChunk(x / 128, y / 128).addTile((x / 16) % 8, (y / 16) % 8, tile);

        if (tile.hasDataContainer) {
            ((TileWithDataContainer) tile).createDataContainer(x, y, x, y);
        }
    }
    public static void addSpawner(int x, int y, Class type) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        Level.getChunk(x / 128, y / 128).addSpawner((x / 16) % 8, (y / 16) % 8, new EntitySpawner(type.getName(), (x / 16) * 16, (y / 16) * 16));
    }

    public static void removeTile(int x, int y) {
        Level.getChunk(x / 128, y / 128).removeTile((x / 16) % 8, (y / 16) % 8);
    }
    public static void removeSpawner(int x, int y) {
        Level.getChunk(x / 128, y / 128).removeSpawner((x / 16) % 8, (y / 16) % 8);
    }
    public static Chunk getChunk(int i) {
        if (i < 0 || i >= Level.totalChunks) {
            return DummyChunk.c;
        }
        else if (Level.chunks[i] == null) {
            Level.chunks[i] = new Chunk();
        }
        return Level.chunks[i];
    }
    public static Chunk getChunk(int x, int y) {
        return Level.getChunk(y * (Game.OBJWIDTH / 128) + x);
    }

    public static void initiateChunks() {
        for (int i = 0; i < Level.totalChunks; i++) {
            Level.chunks[i] = new Chunk();
        }
    }
    public static void loadTileset() {
        Level.tileset.add(null);
        Level.tileset.add(new BasicFloortile());
        Level.tileset.add(new BasicCeilingtile());
        Level.tileset.add(new BasicWalltileLeft());
        Level.tileset.add(new BasicWalltileRight());
        Level.tileset.add(new BasicCornertileTopLeft());
        Level.tileset.add(new BasicCornertileTopRight());
        Level.tileset.add(new BasicCornertileBottomRight());
        Level.tileset.add(new BasicCornertileBottomLeft());
        Level.tileset.add(new BasicInnerCornertileTopLeft());
        Level.tileset.add(new BasicInnerCornertileTopRight());
        Level.tileset.add(new BasicInnerCornertileBottomRight());
        Level.tileset.add(new BasicInnerCornertileBottomLeft());
        Level.tileset.add(new BasicTile());
        Level.tileset.add(new GreenGoo());
        Level.tileset.add(new BlueGoo());
        Level.tileset.add(new UpOneWayBarrier());
        Level.tileset.add(new RightOneWayBarrier());
        Level.tileset.add(new DownOneWayBarrier());
        Level.tileset.add(new LeftOneWayBarrier());
        Level.tileset.add(new SpikyBall());
        Level.tileset.add(new UpSpikes());
        Level.tileset.add(new RightSpikes());
        Level.tileset.add(new DownSpikes());
        Level.tileset.add(new LeftSpikes());
        Level.tileset.add(new AcidSurface());
        Level.tileset.add(new Acid());
        Level.tileset.add(new DecorativeMetalTile());
        Level.tileset.add(new DecorativeMetalTile2());
        Level.tileset.add(new Pedestal());
        Level.tileset.add(new HorizontalSmallBulletTurret());
    }
    public static void loadEntityset() {
        Level.entityset.add(null);
        Level.entityset.add(SimpleMook.class);
        Level.entityset.add(RollingPlatform.class);
        Level.entityset.add(MotionlessBlob.class);
        Level.entityset.add(LevelObjective.class);
        Level.entityset.add(Battery.class);
        Level.entityset.add(SmallBullet.class);
    }
    public static void addSpawnpoint(int x, int y) {
        Spawnpoint.x = x;
        Spawnpoint.y = y;
        Spawnpoint.set = true;

        Controls.player.x = x;
        Controls.player.y = y;
    }
}