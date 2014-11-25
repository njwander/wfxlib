package wfx.utils.test;

/**
 * Created by fanxin.wfx on 14-11-16.
 */
public class FinallyRun {
    private int a = 0;

    public int getInt() {
        try {
            a = 1;
            return a;
        } finally {
            a = 2;
            return a;
        }
    }

    public static void main(String[] args) {
        FinallyRun f = new FinallyRun();
        int tmp = f.getInt();
        System.out.println("f.getInt() = " + tmp);
        System.out.println("f.a = " + f.a);
    }
}
