package pressjumptospace.level;

import pressjumptospace.entity.CursorBoundSpawner;
import pressjumptospace.entity.CursorBoundTile;
import pressjumptospace.main.Game;

public class LevelEditor {
    public static CursorBoundTile selectedTile;
    public static CursorBoundSpawner selectedSpawner;
    public static boolean eraser = false;
    public static boolean spawnpoint = false;
    public static boolean pressed = false;
    public static int mode = 0;

    public static void toggleMode() {
        LevelEditor.mode = (LevelEditor.mode == 0) ? 1 : 0;

        Game.paletteFrame.setTitle((LevelEditor.mode == 0) ? "Tiles" : "Entities");
    }
}