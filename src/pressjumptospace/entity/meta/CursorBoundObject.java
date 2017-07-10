package pressjumptospace.entity.meta;

import pressjumptospace.entity.Eraser;
import pressjumptospace.level.LevelEditor;
import pressjumptospace.level.Spawnpoint;
import pressjumptospace.render.Sprite;
import pressjumptospace.util.Util;

public abstract class CursorBoundObject extends AbstractEntity {
    public CursorBoundObject(int x_, int y_, String sprite_) {
        super(x_, y_, new boolean[] {false, false, false, false}, new int[] {0, 0, 0, 0}, sprite_);
    }

    public static String getSprite() {
        if (LevelEditor.eraser) {
            return Eraser.sprite;
        }
        else if (LevelEditor.spawnpoint) {
            return Spawnpoint.sprite;
        }
        else {
            Util.err("CursorBoundObject sprite couldn't be found.");
            return Sprite.nul;
        }
    }
}
