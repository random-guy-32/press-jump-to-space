package pressjumptospace.util;

public class Util {
    // don't ask
    public static int DEBUGCOUNTER = 0;
    public static int DEBUGCOUNTER2 = 0;

    public static void log(String s) {
        System.out.println(s);
    }
    public static void log(byte s) {
        System.out.println(s);
    }
    public static void log(short s) {
        System.out.println(s);
    }
    public static void log(int s) {
        System.out.println(s);
    }
    public static void log(long s) {
        System.out.println(s);
    }
    public static void log(float s) {
        System.out.println(s);
    }
    public static void log(double s) {
        System.out.println(s);
    }
    public static void log(boolean s) {
        System.out.println(s);
    }
    public static void log(char s) {
        System.out.println(s);
    }
    public static void log(Object s) {
        System.out.println(s);
    }

    public static void err(String s) {
        System.err.println("ERROR: " + s);
    }

    public static int random(int lower, int upper) {
        return (int) (Math.floor(Math.random() * (upper - lower) + lower));
    }
    public static int roundToN(int value, int n) {
        return (value / n) * n;
    }
    public static int lMax(int[] l) {
        int result = 0;
        for (int i = 0; i < l.length; i++) {
            result = Math.max(result, l[i]);
        }
        return result;
    }
    public static short lMax(short[] l) {
        short result = 0;
        for (int i = 0; i < l.length; i++) {
            result = (short) Math.max(result, l[i]);
        }
        return result;
    }

    public static char oppositeDir(char dir) {
        switch (dir) {
            case 'u':
                return 'd';
            case 'r':
                return 'l';
            case 'd':
                return 'u';
            case 'l':
                return 'r';
            default:
                Util.err("Unknown directional argument. Assuming default value 'u'.");
                return 'u';
        }
    }
}
