package pressjumptospace.entity.meta;

import pressjumptospace.render.Sprite;

public abstract class NPC extends Entity {
    public NPC() {
        super(0, 0, new boolean[] {false, false, false, false}, new int[] {0, 0, 0, 0}, 16, 16, 0, 0, Sprite.nul, (byte) 0, false, 20, 20);
    }
    public NPC(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_);
    }
    public NPC(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, vulnerable_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_, hurtByTile, hurtByEntity);
    }

    public abstract void ai();
}