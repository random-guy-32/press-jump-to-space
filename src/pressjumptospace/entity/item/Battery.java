package pressjumptospace.entity.item;

import pressjumptospace.entity.meta.HealthUpgrade;

public class Battery extends HealthUpgrade {
    public Battery() {
        super(0, 0, 12, 15, 2, 1, "item/battery.png", false, 1);

        entityID = 5;
    }
    public Battery(int x_, int y_) {
        super(x_, y_, 12, 15, 2, 1, "item/battery.png", false, 1);

        entityID = 5;
    }
}
