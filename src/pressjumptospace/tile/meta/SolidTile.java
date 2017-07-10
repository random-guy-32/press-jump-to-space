package pressjumptospace.tile.meta;

public abstract class SolidTile extends Tile {
    public SolidTile(int[] hurts_, Material material_, String sprite_, String name_) {
        super(new boolean[] {true, true, true, true}, hurts_, material_, sprite_, name_);
    }
    public SolidTile(Material material_, String sprite_, String name_) {
        super(new boolean[] {true, true, true, true}, material_, sprite_, name_);
    }
}