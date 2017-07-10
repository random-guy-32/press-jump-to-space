package pressjumptospace.entity.npc;

import pressjumptospace.entity.meta.PatrollingEnemy;

public class RollingPlatform extends PatrollingEnemy {
    public RollingPlatform() {
        super(0, 0, new boolean[] {true, true, true, true}, new int[] {0, 0, 0, 0}, new boolean[] {false, false, false, false}, 16, 16, 0, 0, new String[] {"entity/rolling-platform-right.png", "entity/rolling-platform-right.png", "entity/rolling-platform-left.png", "entity/rolling-platform-left.png"}, (byte) 0, true, 1, 1, false, false);
        walkSpeed = 1;
        entityID = 2;
    }
    public RollingPlatform(int x_, int y_, byte dir_) {
        super(x_, y_, new boolean[] {true, true, true, true}, new int[] {0, 0, 0, 0}, new boolean[] {false, false, false, false}, 16, 16, 0, 0, new String[] {"entity/rolling-platform-right.png", "entity/rolling-platform-right.png", "entity/rolling-platform-left.png", "entity/rolling-platform-left.png"}, dir_, true, 1, 1, false, false);
        walkSpeed = 1;
        entityID = 2;
    }
}
