package pressjumptospace.entity.npc;

import pressjumptospace.entity.meta.PatrollingEnemy;

public class SimpleMook extends PatrollingEnemy {
    public SimpleMook() {
        super(0, 0, new boolean[] {true, true, true, true}, new int[] {0, 1, 1, 1}, new boolean[] {true, false, false, false}, 14, 13, 1, 3, new String[] {"entity/basic-mook-right.png", "entity/basic-mook-right.png", "entity/basic-mook-left.png", "entity/basic-mook-left.png"}, (byte) 0, true, 1, 1, false, false);
        walkSpeed = 3;

        entityID = 1;
    }
    public SimpleMook(int x_, int y_, byte dir_, int health_) {
        super(x_, y_, new boolean[] {true, true, true, true}, new int[] {0, 1, 1, 1}, new boolean[] {true, false, false, false}, 14, 13, 1, 3, new String[] {"entity/basic-mook-right.png", "entity/basic-mook-right.png", "entity/basic-mook-left.png", "entity/basic-mook-left.png"}, dir_, true, health_, 1, false, false);
        walkSpeed = 3;

        entityID = 1;
    }
}