/*
 * Decompiled with CFR 0_114.
 */
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Report {
    private BankLine waiting;
    private List<Customer> served = new ArrayList<Customer>();
    private double timeNow;
    public static final int LINE_HEIGHT = 15;

    public Report(BankLine theLineToMonitor) {
        this.waiting = theLineToMonitor;
        this.timeNow = 0.0;
    }

    public synchronized void updateTime(double newTime) {
        this.timeNow = newTime;
    }

    public synchronized void addServed(Customer c) {
        this.served.add(c);
    }

    public synchronized double averageOfLine() {
        double total = 0.0;
        int count = 0;
        Iterator<Customer> iter = this.waiting.iterator();
        while (iter.hasNext()) {
            Customer c = iter.next();
            double waitSoFar = this.timeNow - c.arrivalTime();
            ++count;
            total += waitSoFar;
        }
        return total / (double)count;
    }

    public synchronized int waitingCount() {
        int count = 0;
        Iterator<Customer> iter = this.waiting.iterator();
        while (iter.hasNext()) {
            iter.next();
            ++count;
        }
        return count;
    }

    public synchronized double averageOfServed() {
        double total = 0.0;
        int count = 0;
        for (Customer c : this.served) {
            double waitSoFar = c.waitTime();
            ++count;
            total += waitSoFar;
        }
        return total / (double)count;
    }

    public synchronized void drawOn(Graphics g, int leftX, int topY) {
        String toDraw = "Simulation time is: " + this.timeNow;
        g.drawString(toDraw, leftX, topY + 15);
        int count = this.waitingCount();
        toDraw = "Customers waiting: " + count;
        g.drawString(toDraw, leftX, topY + 30);
        toDraw = count < 1 ? "    No average yet." : "    Average time spent waiting: " + this.averageOfLine();
        g.drawString(toDraw, leftX, topY + 45);
        count = this.served.size();
        toDraw = "Customers served: " + count;
        g.drawString(toDraw, leftX, topY + 60);
        toDraw = count < 1 ? "    No average yet." : "    Average time spent waiting: " + this.averageOfServed();
        g.drawString(toDraw, leftX, topY + 75);
    }
}

