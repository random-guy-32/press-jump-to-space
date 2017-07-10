package pressjumptospace.tile.meta;

/**
 * this is dumb
 */
public abstract class TileWithDataContainer extends Tile {
    public TileWithDataContainer(boolean[] solid_, int[] hurts_, Material material_, String sprite_, String name_) {
        super(solid_, hurts_, material_, true, sprite_, name_);
    }

    public TileDataContainer tileEntity;

    public void action() {
        this.tileEntity.action();
    }
    public abstract void createDataContainer(int x, int y, int tileX, int tileY);
}
