package pressjumptospace.tile.meta;

public abstract class OneWayBarrier extends Tile {
    public OneWayBarrier(boolean[] solid_, String sprite_, String name_) {
        super(solid_, Material.Metal, sprite_, name_);
    }
}
