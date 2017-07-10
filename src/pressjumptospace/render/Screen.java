package pressjumptospace.render;

import pressjumptospace.util.Util;

public class Screen {
    public static int x = 0;
    public static int y = 0;
    public static int scrollAmount = 1;

    public static void moveScreen(char axis, int amount) {
        switch (axis) {
            case 'x':
                if (Screen.x > 0 && amount < 0) {
                    Screen.x += Math.min(-Screen.x, amount);
                }
                else if (amount > 0) {
                    Screen.x += amount;
                }
                break;
            case 'y':
                if (Screen.y > 0 && amount < 0) {
                    Screen.y += Math.min(-Screen.x, amount);
                }
                else if (amount > 0) {
                    Screen.y += amount;
                }
                break;
            default:
                Util.err("Unknown axis for screen repositioning.");
        }
    }
}
