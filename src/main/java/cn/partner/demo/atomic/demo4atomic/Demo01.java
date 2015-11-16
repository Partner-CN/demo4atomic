package cn.partner.demo.atomic.demo4atomic;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;

public class Demo01 {

    public static void main(String[] args) throws InterruptedException {
        Increaser increaser = new NoAtomic();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> increaser.work()).start();
        }

        Thread.sleep(500);
        System.out.println(increaser.getValue());
    }
}


abstract class Increaser {

    protected abstract int getValue();

    protected abstract void increase();

    public void work() {
        for (int j = 0; j < 1000; j++) {
            increase();
        }
    }
}


@Getter
@Setter
class NoAtomic extends Increaser {
    int i;

    @Override
    protected void increase() {
        i++;
    }

    @Override
    protected int getValue() {
        return i;
    }
}


class UseAtomic extends Increaser {
    AtomicInteger i = new AtomicInteger(0);

    @Override
    protected int getValue() {
        return i.get();
    }

    @Override
    protected void increase() {
        i.getAndIncrement();
    }

}
