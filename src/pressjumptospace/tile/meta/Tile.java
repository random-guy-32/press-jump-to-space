package pressjumptospace.tile.meta;

import pressjumptospace.entity.meta.DynamicObject;
import pressjumptospace.level.Level;

public abstract class Tile extends DynamicObject {
    public Tile(boolean[] solid_, int[] hurts_, Material material_, String sprite_, String name_) {
        super(solid_, hurts_, sprite_);
        material = material_;
        name = name_;
        tileID = Tile.idIndex;
        hasDataContainer = false;
        Tile.idIndex++;
    }
    public Tile(boolean[] solid_, Material material_, String sprite_, String name_) {
        super(solid_, new int[] {0, 0, 0, 0}, sprite_);
        material = material_;
        name = name_;
        tileID = Tile.idIndex;
        hasDataContainer = false;
        Tile.idIndex++;
    }
    public Tile(boolean[] solid_, int[] hurts_, Material material_, boolean hasDataContainer_, String sprite_, String name_) {
        super(solid_, hurts_, sprite_);
        material = material_;
        name = name_;
        tileID = Tile.idIndex;
        hasDataContainer = hasDataContainer_;
        Tile.idIndex++;
    }
    public Tile(boolean[] solid_, Material material_, boolean hasDataContainer_, String sprite_, String name_) {
        super(solid_, new int[] {0, 0, 0, 0}, sprite_);
        material = material_;
        name = name_;
        tileID = Tile.idIndex;
        hasDataContainer = hasDataContainer_;
        Tile.idIndex++;
    }

    public String name;
    public short tileID;
    public Material material;
    public boolean hasDataContainer;

    public static short idIndex = 1;    // There's got to be a better way!

    public static Tile byID(short i) {
        return Level.tileset.get(i);
    }
}