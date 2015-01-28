package wfx.utils.test;

/**
 * Created by admin on 14-6-26.
 */
public class Test {
    public static void main(String[] args) {
        int r = 255, g = 255, b = 255;
        int gray = (r * 19595 + g * 38469 + b * 7472) >> 18;
        System.out.println(gray);
    }
}
