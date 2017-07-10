package pressjumptospace.entity;

import pressjumptospace.entity.meta.Entity;

public class LevelObjective extends Entity {
    public LevelObjective() {
        super(0, 0, new boolean[] {false, false, false, false}, new int[] {0, 0, 0, 0}, 14, 14, 1, 1, "entity/objective.png", (byte) 0, false, 1, 1);
        entityID = 4;
    }
    public LevelObjective(int x_, int y_) {
        super(x_, y_, new boolean[] {false, false, false, false}, new int[] {0, 0, 0, 0}, 14, 14, 1, 1, "entity/objective.png", (byte) 0, false, 1, 1);
        entityID = 4;
    }
}
