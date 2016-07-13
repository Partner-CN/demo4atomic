package cn.partner.demo.atomic.demo4atomic;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;
import cn.partner.demo.atomic.demo4atomic.common.Increaser;

/**
 * compare atomic and simple i++ with synchronized
 */
public class Demo02 {

    public static void main(String[] args) throws InterruptedException {
        Increaser simpleIncre = new SimpleIncre();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> simpleIncre.work()).start();
        }

        Thread.sleep(500);
        System.out.println("simpleIncre : " + simpleIncre.getValue());


        Increaser atomicIncre = new AtomicIncre();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> atomicIncre.work()).start();
        }

        Thread.sleep(500);
        System.out.println("atomicIncre : " + atomicIncre.getValue());
    }

    @Getter
    @Setter
    static class SimpleIncre extends Increaser {
        int i;

        @Override
        protected synchronized void increase() {
            int result = i++;
            if (result == 9999) {
                System.out.println("simpleCost : " + (System.currentTimeMillis() - start));
            }
        }

        @Override
        public int getValue() {
            return i;
        }
    }

    static class AtomicIncre extends Increaser {
        AtomicInteger i = new AtomicInteger(0);

        @Override
        public int getValue() {
            return i.get();
        }

        @Override
        protected void increase() {
            int result = i.getAndIncrement();
            if (result == 9999) {
                System.out.println("atomicCost : " + (System.currentTimeMillis() - start));
            }
        }
    }
}
