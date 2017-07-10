package pressjumptospace.entity.projectile;

import pressjumptospace.entity.meta.Projectile;


public class SmallBullet extends Projectile {
    public SmallBullet(int x_, int y_, byte dir_) {
        super(x_, y_, new boolean[] {true, true, true, true}, new int[] {0, 1, 1, 1}, new boolean[] {true, false, false, false}, 2, 2, 7, 7, "entity/small-bullet.png", dir_, false, 1, 1, false, false, false, false, "default", 2f);
    }
}
