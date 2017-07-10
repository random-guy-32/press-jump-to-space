package pressjumptospace.entity.meta;

import pressjumptospace.render.Renderable;
import pressjumptospace.render.Sprite;

public abstract class Particle implements Renderable{
    public Particle() {

    }

    public Sprite sprite;
    public int x;
    public int y;
    public int width;
    public int height;
    public float velX;
    public float velY;
    public int age;
    public float frictionX;
    public float frictionY;
    public float gravityModifier;
    public boolean tangible;
}
