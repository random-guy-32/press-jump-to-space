package pressjumptospace.tile.meta;

public abstract class LiquidTile extends Tile {
    public LiquidTile(String sprite_, String name_, Material material_,int[] hurts_, float viscosity_) {
        super(new boolean[] {false, false, false, false}, hurts_, material_, sprite_, name_);
        viscosity = viscosity_;
    }

    public float viscosity;
}
