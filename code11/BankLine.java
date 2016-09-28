/*
 * Decompiled with CFR 0_114.
 */
import QueuePackage.QueueInterface;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.Vector;

public class BankLine
implements QueueInterface<Customer> {
    private Vector<Customer> queue = new Vector();
    public static final int LINE_SPACE = 60;
    public static final int CUSTOMERS_TO_DRAW = 5;

    @Override
    public synchronized void enqueue(Customer newEntry) {
        this.queue.add(newEntry);
    }

    @Override
    public synchronized Customer getFront() {
        Customer front = null;
        if (!this.isEmpty()) {
            front = this.queue.get(0);
        }
        return front;
    }

    @Override
    public synchronized Customer dequeue() {
        Customer front = null;
        if (!this.isEmpty()) {
            front = this.queue.remove(0);
        }
        return front;
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public synchronized void clear() {
        this.queue.clear();
    }

    public synchronized Iterator<Customer> iterator() {
        return this.queue.iterator();
    }

    public synchronized void drawOn(Graphics g, int leftX, int baseY) {
        if (this.queue.size() == 0) {
            String toDraw = "No customers in line";
            g.drawString(toDraw, leftX, baseY);
        } else {
            int positionX = leftX;
            Iterator<Customer> iter = this.queue.iterator();
            for (int i = 0; i < 5 && iter.hasNext(); ++i) {
                iter.next().drawOn(g, positionX, baseY);
                positionX += 60;
            }
            if (iter.hasNext()) {
                int remaining = this.queue.size() - 5;
                String toDraw = ". . . " + remaining + " more customers";
                g.drawString(toDraw, positionX, baseY);
            }
        }
    }
}

