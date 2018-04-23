package pressjumptospace.menu;

import pressjumptospace.render.Texture;

import java.awt.*;

public abstract class GraphicalInterfaceComponent {
    public GraphicalInterfaceComponent(int x_, int y_, int w, int h) {
        x = x_;
        y = y_;
        width = w;
        height = h;

        backgroundColor = new Color(0xff, 0xff, 0xff, 0);
    }

    public int x;
    public int y;
    public int width;
    public int height;

    public Color backgroundColor;
    public Texture backgroundTexture;
}
