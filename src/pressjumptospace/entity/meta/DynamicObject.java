package pressjumptospace.entity.meta;

import pressjumptospace.render.Renderable;
import pressjumptospace.render.Sprite;
import pressjumptospace.util.Util;

import java.awt.*;

public abstract class DynamicObject implements Renderable {
    public DynamicObject(boolean[] solid_, int[] hurts_, String sprite_) {
        sprite = new Sprite(sprite_);
        solid = solid_;
        hurts = hurts_;
    }

    public Sprite sprite;
    public Hitbox hitbox;
    public boolean[] solid;
    public int[] hurts;

    public boolean isSolid(String dir) {
        switch (dir) {
            case "top":
                return this.solid[0];
            case "right":
                return this.solid[1];
            case "bottom":
                return this.solid[2];
            case "left":
                return this.solid[3];
            default:
                Util.err("Invalid argument in DynamicObject.isSolid(String).");
                return false;
        }
    }
    public int getAverageHurts() {
        int result = 0;

        for (int i = 0; i < this.hurts.length; i++) {
            result += this.hurts[i];
        }

        return result / this.hurts.length;
    }

    public void render(Graphics g) {
        this.sprite.render(g);
    }
    public void render(Graphics g, int x, int y) {
        this.sprite.render(g, x, y);
    }
}