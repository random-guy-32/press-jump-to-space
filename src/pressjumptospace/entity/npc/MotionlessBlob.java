package pressjumptospace.entity.npc;

import pressjumptospace.entity.meta.NPC;

public class MotionlessBlob extends NPC {
    public MotionlessBlob() {
        super(0, 0, new boolean[] {true, true, true, true}, new int[] {0, 0, 0, 0}, new boolean[] {false, false, false, false}, 16, 16, 0, 0, "entity/motionless-blob.png", (byte) 0, false, 1, 1, false, false);

        entityID = 3;
    }
    public MotionlessBlob(int x_, int y_, byte dir_) {
        super(x_, y_, new boolean[] {true, true, true, true}, new int[] {0, 0, 0, 0}, new boolean[] {false, false, false, false}, 16, 16, 0, 0, "entity/motionless-blob.png", dir_, false, 1, 1, false, false);

        entityID = 3;
    }

    public void ai() {}
}
