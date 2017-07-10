package pressjumptospace.entity.meta;

public abstract class Enemy extends NPC {
    public Enemy(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_);
    }
    public Enemy(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, vulnerable_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_, hurtByTile, hurtByEntity);
    }
}