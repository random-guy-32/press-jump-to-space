package pressjumptospace.entity;

import pressjumptospace.entity.item.Battery;
import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.npc.MotionlessBlob;
import pressjumptospace.entity.npc.RollingPlatform;
import pressjumptospace.entity.npc.SimpleMook;
import pressjumptospace.entity.projectile.SmallBullet;
import pressjumptospace.level.Level;
import pressjumptospace.render.Renderable;
import pressjumptospace.render.Sprite;
import pressjumptospace.util.Util;

import java.awt.*;

public class EntitySpawner implements Renderable {
    public EntitySpawner(String type_) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        type = Class.forName(type_);
        sprite = ((Entity) type.newInstance()).sprite;
        entityID = ((Entity) type.newInstance()).entityID;
    }
    public EntitySpawner(String type_, int x_, int y_) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        type = Class.forName(type_);
        sprite = ((Entity) type.newInstance()).sprite;
        entityID = ((Entity) type.newInstance()).entityID;
        x = x_;
        y = y_;
        sprite.x = x_;
        sprite.y = y_;
    }

    public Class type;
    public short entityID;

    public int x;
    public int y;
    public Sprite sprite;

    public void render(Graphics g) {
        this.sprite.render(g);
    }
    public void render(Graphics g, int x, int y) {
        this.sprite.render(g, x, y);
    }

    public void spawn() {
        /*
        try {
            Entity e = (Entity) this.type.newInstance();

            e.x = this.x;
            e.y = this.y;

            Util.log(e.dir);
        }
        catch (InstantiationException|IllegalAccessException ex) {
            Util.err("Unknown entity type '" + this.type.getName() + "'. Cannot spawn entity at (" + this.x + ", " + this.y + ").");
        }
        */

        switch (this.type.getName()) {
            case "pressjumptospace.entity.npc.SimpleMook":
                Level.entities.add(new SimpleMook(this.x, this.y, (byte) 3, 1));
                break;
            case "pressjumptospace.entity.npc.RollingPlatform":
                Level.entities.add(new RollingPlatform(this.x, this.y, (byte) 3));
                break;
            case "pressjumptospace.entity.npc.MotionlessBlob":
                Level.entities.add(new MotionlessBlob(this.x, this.y, (byte) 3));
                break;
            case "pressjumptospace.entity.LevelObjective":
                Level.entities.add(new LevelObjective(this.x, this.y));
                break;
            case "pressjumptospace.entity.item.Battery":
                Level.entities.add(new Battery(this.x, this.y));
                break;
            case "pressjumptospace.entity.projectile.SmallBullet":
                Level.entities.add(new SmallBullet(this.x, this.y, (byte) 3));
                break;
            default:
                Util.err("Unknown entity type '" + this.type.getName() + "'. Cannot spawn entity at (" + this.x + ", " + this.y + ").");
        }

    }
}
