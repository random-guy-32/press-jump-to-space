package pressjumptospace.tile.meta;

public abstract class GooTile extends LiquidTile {
    public GooTile(String sprite_, String name_, int[] hurts_, float viscosity_) {
        super(sprite_, name_, Material.Goo, hurts_, viscosity_);
    }
}
