package deepin.atomicreference;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by li on 2017/9/22.
 */

public class AtomicIntegerTest {
    AtomicInteger counter = new AtomicInteger(1);

    public int counterAddOne() {
        int result;
        boolean flag;
        do {
            result = counter.get();
            // 单线程下, compareAndSet返回永远为true,
            // 多线程下, 在与result进行compare时, counter可能被其他线程set了新值, 这时需要重新再取一遍再比较,
            // 如果还是没有拿到最新的值, 则一直循环下去, 直到拿到最新的那个值
            flag = counter.compareAndSet(result, result + 1);
        } while (!flag);

        System.out.println(" current data = " + result);
        return result;
    }

    public static void main(String[] args) {
        final AtomicIntegerTest c = new AtomicIntegerTest();
        new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 100; ++i){
                    c.counterAddOne();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 100; ++i){
                    c.counterAddOne();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for(int i = 0; i < 100; ++i){
                    c.counterAddOne();
                }
            }
        }.start();
    }
}
