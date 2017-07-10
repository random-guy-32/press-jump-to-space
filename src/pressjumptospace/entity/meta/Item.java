package pressjumptospace.entity.meta;

public abstract class Item extends Entity {
    public Item(int x_, int y_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, boolean willFall_) {
        super(x_, y_, new boolean[]{false, false, false, false}, new int[] {0, 0, 0, 0}, new boolean[] {false, false, false, false}, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, (byte) 0, willFall_, 1, 1, false, false);
    }
}
