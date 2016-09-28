/*
 * Decompiled with CFR 0_114.
 */
import java.awt.Component;

public final class Stepper {
    private Object dispatcher;
    private Component display;
    private int onStep;
    private State status;

    public Stepper(Object d, Component x) {
        this.dispatcher = d;
        this.display = x;
        this.onStep = 0;
        this.status = State.SETUP;
    }

    public int getStep() {
        return this.onStep;
    }

    public State getStatus() {
        return this.status;
    }

    public void setupStep() {
        this.status = State.SETUP;
        this.display.repaint();
        try {
            Object object = this.dispatcher;
            synchronized (object) {
                this.dispatcher.wait();
            }
        }
        catch (InterruptedException e) {
            // empty catch block
        }
    }

    public void initialStateStep() {
        this.status = State.INITIAL;
        this.onStep = 0;
        this.display.repaint();
        try {
            Object object = this.dispatcher;
            synchronized (object) {
                this.dispatcher.wait();
            }
        }
        catch (InterruptedException e) {
            // empty catch block
        }
    }

    public void animationStep() {
        this.status = State.STEPPING;
        ++this.onStep;
        this.display.repaint();
        try {
            Object object = this.dispatcher;
            synchronized (object) {
                this.dispatcher.wait();
            }
        }
        catch (InterruptedException e) {
            // empty catch block
        }
    }

    public void finalStep() {
        this.status = State.FINAL;
        this.display.repaint();
        try {
            Object object = this.dispatcher;
            synchronized (object) {
                this.dispatcher.wait();
            }
        }
        catch (InterruptedException e) {
            // empty catch block
        }
    }

    public static enum State {
        SETUP,
        INITIAL,
        STEPPING,
        FINAL;
        

        private State() {
        }
    }

}

