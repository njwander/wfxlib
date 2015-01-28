package wfx.check;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.ArrayCtxImpl;
import com.greenpineyu.fel.context.FelContext;

import java.util.Calendar;

/**
 * Created by fanxin.wfx on 14-10-31.
 */
public class FelCheck {
    FelEngine fel = new FelEngineImpl();

    /**
     * 验证FelEngine的fel是否是线程安全
     * 结论：
     * 1.FelEngine.getContext()多次调用，获取的是同一个对象
     * 2.FelConext对象，缺少清空上线的方法
     * 3.FelConext对象是线程复用
     */
    public void checkContextThreadSafe() {
        {
            ContextThread thread = new ContextThread(12);
            thread.start();
            System.out.println("第一个构建完成");
        }
        {
            ContextThread thread = new ContextThread(13);
            thread.start();
            System.out.println("第二个构建完成");
        }
    }

    class ContextThread extends Thread {
        private int num;

        public ContextThread(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            FelContext context = new ArrayCtxImpl();
            context.set("a", num);
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结果：" + fel.eval("a + a", context));
        }
    }

    public static void main(String[] args) {
        FelCheck felCheck = new FelCheck();
        felCheck.checkContextThreadSafe();
    }
}
