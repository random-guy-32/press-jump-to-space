package pressjumptospace.render;

import pressjumptospace.entity.meta.AbstractDynamicComponent;
import pressjumptospace.util.SpriteManager;

import java.awt.*;

public class Sprite extends AbstractDynamicComponent implements Renderable {
    public Sprite(String src_) {
        super(0, 0, 0, 0);
        src = src_;
    }
    public Sprite(int x_, int y_, String src_) {
        super(x_, y_, 0, 0);
        src = src_;
    }
    public Sprite(int x_, int y_, int offsetX_, int offsetY_, String src_) {
        super(x_, y_, offsetX_, offsetY_);
        src = src_;
    }

    public String src;

    public static String nul = "misc/null.png";

    public void render(Graphics g) {
        g.drawImage(SpriteManager.loadImage(this.src), this.pos('x'), this.pos('y'), null);
    }
    public void render(Graphics g, int x, int y) {
        g.drawImage(SpriteManager.loadImage(this.src), x, y, null);
    }
}