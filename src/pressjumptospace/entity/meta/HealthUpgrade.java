package pressjumptospace.entity.meta;


public abstract class HealthUpgrade extends Item {
    public HealthUpgrade(int x_, int y_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, boolean willFall_, int heals_) {
        super(x_, y_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, willFall_);
        heals = heals_;
    }

    public int heals;
}
