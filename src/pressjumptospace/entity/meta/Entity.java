package pressjumptospace.entity.meta;

import pressjumptospace.entity.EntityHitbox;
import pressjumptospace.entity.player.Player;
import pressjumptospace.main.Game;
import pressjumptospace.render.Renderable;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.util.Util;

import java.awt.*;

public abstract class Entity extends AbstractEntity implements Renderable {
    public Entity(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, sprite_);
        dir = dir_;
        velX = 0f;
        velY = 0f;
        willFall = willFall_;
        maxHealth = maxHealth_;
        health = health_;
        width = 16;
        height = 16;
        vulnerable = new boolean[] {true, true, true, true};
        hitbox = new EntityHitbox(solid_, x_, y_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY);
        isHurtByTile = false;
        isHurtByEntity = false;
        damageCooldown = 0;
        maxDamageCooldown = 80;
    }
    public Entity(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, sprite_);
        dir = dir_;
        velX = 0f;
        velY = 0f;
        willFall = willFall_;
        maxHealth = maxHealth_;
        health = health_;
        width = 16;
        height = 16;
        vulnerable = vulnerable_;
        isHurtByTile = hurtByTile;
        isHurtByEntity = hurtByEntity;
        hitbox = new EntityHitbox(solid_, x_, y_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY);
        damageCooldown = 0;
        maxDamageCooldown = 80;
    }

    public byte dir;
    public float velX;
    public float velY;
    public boolean willFall;
    public int health;
    public int maxHealth;
    public int width;
    public int height;
    public float walkSpeed;
    public boolean[] vulnerable;
    public boolean isHurtByTile;
    public boolean isHurtByEntity;  // only affects non-player entities
    public int damageCooldown;
    public int maxDamageCooldown;

    public short entityID;

    public void jump(float vel) {
        this.velY = vel;
    }
    public void walk(float vel) {
        if (this.dir == 1) {
            this.velX = vel;
        }
        else if (this.dir == 3) {
            this.velX = -vel;
        }
    }

    public void heal(int amount) {
        this.setHealth(this.health + amount);
    }
    public void hurt(int amount) {
        if (this.damageCooldown == 0) {
            this.setHealth(this.health - amount);
            if (amount > 0) {
                this.damageCooldown = this.maxDamageCooldown;
            }
        }
    }
    public void hurt(int amount, int cooldown) {
        this.setHealth(this.health - amount);
        this.damageCooldown = cooldown;
    }
    public void setHealth(int amount) {
        if (amount < 0) {
            this.health = 0;
        }
        else if (amount > this.maxHealth) {
            this.health = this.maxHealth;
        }
        else {
            this.health = amount;
        }

        if (this instanceof Player) {
            ((Player) this).adjustHealthSprite();
        }
    }
    public void tickDamageCooldown() {
        if (this.damageCooldown > 0) {
            this.damageCooldown--;
        }
    }
    public void kill() {
        this.health = 0;
    }

    public char dirToChar() {
        switch (this.dir) {
            case 0:
                return 'u';
            case 1:
                return 'r';
            case 2:
                return 'd';
            case 3:
                return 'l';
            default:
                Util.err("Unknown direction data. Assuming default value 'u'.");
                return 'u';
        }
    }

    public byte dirCharToByte(char dir) {
        switch (dir) {
            case 'u':
                return 0;
            case 'r':
                return 1;
            case 'd':
                return 2;
            case 'l':
                return 3;
            default:
                Util.err("Unknown direction data. Assuming default value 0.");
                return 0;
        }
    }

    public void setDir(char dir) {
        switch (dir) {
            case 'u':
                this.dir = 0;
                break;
            case 'r':
                this.dir = 1;
                break;
            case 'd':
                this.dir = 2;
                break;
            case 'l':
                this.dir = 3;
                break;
            default:
                Util.err("Unknown direction data. Assuming default value 0.");
                this.dir = 0;
        }
    }

    public void render(Graphics g) {
        // Util.log(this.damageCooldown);
        if (this.damageCooldown == 0 || Game.age % 3 == 0) {
            this.sprite.render(g);
        }
    }
    public void render(Graphics g, int x, int y) {
        if (this.damageCooldown == 0 || Game.age % 3 == 0) {
            this.sprite.render(g, x, y);
        }
    }

    public int pos(char axis) {return this.hitbox.pos(axis);}
    public boolean getTileSolid(byte dir) {
        return ((EntityHitbox) this.hitbox).getTileSolid(dir, this.hitbox.offsetX, this.hitbox.offsetY);
    }

    public int getCollision(char dir) {
        return ((EntityHitbox) this.hitbox).getCollision(dir);
    }
    public int getTileCollision(char dir) {
        return ((EntityHitbox) this.hitbox).getTileCollision(dir);
    }
    public int getEntityCollision(char dir) {
        return ((EntityHitbox) this.hitbox).getEntityCollision(dir);
    }

    public int getTotalHurts() {
        return Math.max(Math.max(this.getHurts('u'), this.getHurts('l')), Math.max(this.getHurts('d'), this.getHurts('r')));
    }
    public int getHurts(char dir) {
        if (this.vulnerable[this.dir]) {
            return Math.max(this.getTileHurts(dir), this.getEntityHurts(dir));
        }
        else return 0;
    }
    public int getTileHurts(char dir) {
        if (this.isHurtByTile) {
            return ((EntityHitbox) this.hitbox).getTileHurts(dir);
        }
        else return 0;
    }
    public int getEntityHurts(char dir) {
        if (this.isHurtByEntity) {
            return ((EntityHitbox) this.hitbox).getEntityHurts(dir);
        }
        else return 0;
    }

    public boolean containsCoordinates(int x, int y) {
        return ((EntityHitbox) this.hitbox).containsCoordinates(x, y);
    }

    public boolean isInTile(Class type) {
        return ((EntityHitbox) this.hitbox).isInTile(type);
    }

    public boolean isInEntity(Class type) {
        return ((EntityHitbox) this.hitbox).isInEntity(type);
    }


    public Tile getTile(String position) {
        return ((EntityHitbox) this.hitbox).getTile(position);
    }
    public float getAverageViscosity() {
        return ((EntityHitbox) this.hitbox).getAverageViscosity();
    }

    public int tileCoords(char axis) {
        if (axis == 'x') {
            return (this.x / 16);
        }
        else if (axis == 'y') {
            return (this.y / 16);
        }
        else {
            Util.err("Incorrect axis value for positioning.");
            return -1;
        }
    }

    public void dealDamage(Entity target, int amount) {
        target.hurt(amount);
    }
    public void dealDamage(Entity target, int amount, int cooldown) {
        target.hurt(amount, cooldown);
    }
}