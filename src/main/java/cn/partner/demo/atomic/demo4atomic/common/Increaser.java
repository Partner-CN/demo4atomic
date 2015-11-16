package cn.partner.demo.atomic.demo4atomic.common;

public abstract class Increaser {

    public abstract int getValue();

    protected abstract void increase();

    public void work() {
        for (int j = 0; j < 1000; j++) {
            increase();
        }
    }
}
