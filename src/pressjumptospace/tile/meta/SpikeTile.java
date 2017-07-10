package pressjumptospace.tile.meta;

public abstract class SpikeTile extends Tile {
    public SpikeTile(int[] hurts_, String sprite_, String name_) {
        super(new boolean[] {true, true, true, true}, hurts_, Material.Metal, sprite_, name_);
    }
}
