package pressjumptospace.entity.meta;

import pressjumptospace.entity.Eraser;
import pressjumptospace.level.LevelEditor;
import pressjumptospace.level.Spawnpoint;
import pressjumptospace.render.Sprite;
import pressjumptospace.util.Util;

/**
 * A spawner or tile that is bound to the mouse cursor in editing mode.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class CursorBoundObject extends AbstractEntity {
    public CursorBoundObject(int x_, int y_, String sprite_) {
        super(x_, y_, new boolean[] {false, false, false, false}, new int[] {0, 0, 0, 0}, sprite_);
    }

    /**
     * If the eraser or spawnpoint is activated in editing mode, the cursor gets its image from this method.
     *
     * @return Either the eraser sprite or the spawnpoint sprite; if this method is called by error, a missing texture is returned.
     */
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
