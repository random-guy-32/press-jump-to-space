package pressjumptospace.util;

import pressjumptospace.entity.meta.Entity;

/**
 * General collection of various useful methods.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class Util {
    /**
     * Outputs text to the console.
     * This method exists because I got tired of writing System.out.println each time.
     *
     * @param s Message.
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Outputs byte to the console.
     *
     * @param s Message.
     */
    public static void log(byte s) {
        System.out.println(s);
    }

    /**
     * Outputs short to the console.
     *
     * @param s Message.
     */
    public static void log(short s) {
        System.out.println(s);
    }

    /**
     * Outputs int to the console.
     *
     * @param s Message.
     */
    public static void log(int s) {
        System.out.println(s);
    }

    /**
     * Outputs long to the console.
     *
     * @param s Message.
     */
    public static void log(long s) {
        System.out.println(s);
    }

    /**
     * Outputs float to the console.
     *
     * @param s Message.
     */
    public static void log(float s) {
        System.out.println(s);
    }

    /**
     * Outputs double to the console.
     *
     * @param s Message.
     */
    public static void log(double s) {
        System.out.println(s);
    }

    /**
     * Outputs boolean to the console.
     *
     * @param s Message.
     */
    public static void log(boolean s) {
        System.out.println(s);
    }

    /**
     * Outputs char to the console.
     *
     * @param s Message.
     */
    public static void log(char s) {
        System.out.println(s);
    }

    /**
     * Outputs object to the console.
     *
     * @param s Message.
     */
    public static void log(Object s) {
        System.out.println(s);
    }

    /**
     * Outputs an error message to the console.
     *
     * @param s Message.
     */
    public static void err(String s) {
        System.err.println("ERROR: " + s);
    }

    /**
     * Generates a random integer between two boundaries.
     * @param lower Lower boundary (inclusive).
     * @param upper Upper boundary (exclusive).
     * @return Random integer.
     */
    public static int random(int lower, int upper) {
        return (int) (Math.floor(Math.random() * (upper - lower) + lower));
    }

    /**
     * Rounds integer down to the nearest multiple of n.
     *
     * @param value Value to be rounded.
     * @param n Number whose integer multiples will be rounded to.
     * @return 'value' rounded down to the nearest integer multiple of 'n'.
     */
    public static int roundToN(int value, int n) {
        return (value / n) * n;
    }

    /**
     * Outputs opposite direction of input.
     *
     * @see pressjumptospace.entity.meta.Entity#dir
     * @see Entity#dirToChar()
     *
     * @param dir Direction (up = 'u'; right = 'r'; down = 'd'; left = 'l').
     * @return The opposite direction.
     */
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
