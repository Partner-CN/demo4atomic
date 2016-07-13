package cn.partner.demo.atomic.demo4atomic.common;

public abstract class Increaser {
    protected long start;

    public Increaser() {
        start = System.currentTimeMillis();
    }


    public abstract int getValue();

    protected abstract void increase();

    public void work() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }
}
