package pressjumptospace.entity.player;

import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.meta.HealthUpgrade;
import pressjumptospace.entity.meta.Item;
import pressjumptospace.level.Level;
import pressjumptospace.level.Spawnpoint;
import pressjumptospace.menu.Console;
import pressjumptospace.util.Util;

import java.awt.*;

public class Player extends Entity {
    public Player(int x_, int y_, byte dir_, int health_) {
        super(x_, y_, new boolean[] {true, true, true, true}, new int[] {0, 0, 1, 0}, new boolean[] {true, true, true, true}, 14, 15, 2, 1, "entity/player-small.png", dir_, true, health_, 3, true, true);
        velX = 0;
        velY = 0;
        triesJumping = false;
        triesWalking = false;
        hasWon = false;
        hasLost = false;

        healthSprites = new String[] {"entity/player/health1.png", "entity/player/health2.png", "entity/player/health3.png"};

        adjustHealthSprite();

        entityID = 0;
    }

    public boolean triesJumping;
    public boolean triesWalking;
    public boolean hasWon;
    public boolean hasLost;

    public String[] healthSprites;

    public void adjustHealthSprite() {
        String sprite;

        switch (this.health) {
            case 3:
                sprite = this.healthSprites[2];
                break;
            case 2:
                sprite = this.healthSprites[1];
                break;
            default:
                sprite = this.healthSprites[0];
        }

        this.sprite.src = sprite;
    }

    public void reset() {
        this.toSpawn();
        this.velX = 0f;
        this.velY = 0f;
        this.hasWon = false;
        this.hasLost = false;
        this.damageCooldown = 0;
        this.setHealth(1);

        if (Level.entities.size() == 0) {
            Level.entities.add(this);
        }
    }

    public void toSpawn() {
        if (Spawnpoint.set) {
            this.x = Spawnpoint.x;
            this.y = Spawnpoint.y;
        }
    }
    public void win() {
        if (!this.hasWon) {
            this.hasWon = true;
            Console.print("You have won.");
        }
    }
    public void lose() {
        if (!this.hasLost) {
            this.hasLost = true;
            Console.print("You have lost.");
        }
    }

    public void collect(Item item) {
        item.kill();

        if (item instanceof HealthUpgrade) {
            this.heal(((HealthUpgrade) item).heals);
        }
    }
}