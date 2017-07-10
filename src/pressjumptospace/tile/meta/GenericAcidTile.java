package pressjumptospace.tile.meta;

public abstract class GenericAcidTile extends LiquidTile {
    public GenericAcidTile(String sprite_, String name_) {
        super(sprite_, name_, Material.Acid, new int[] {10, 10, 10, 10}, 1.0f);
    }
}
