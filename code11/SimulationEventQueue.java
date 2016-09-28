/*
 * Decompiled with CFR 0_114.
 */
import QueuePackage.EmptyQueueException;
import QueuePackage.SimulationEvent;
import QueuePackage.SimulationEventQueueInterface;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Vector;

public class SimulationEventQueue
implements SimulationEventQueueInterface {
    private Vector<SimulationEvent> queue = new Vector();
    private double theCurrentTime = 0.0;

    @Override
    public void add(SimulationEvent newEvent) {
        if (newEvent.getTime() >= this.theCurrentTime) {
            if (this.queue.isEmpty()) {
                this.queue.add(newEvent);
            } else {
                System.out.println("Adding an event " + newEvent + " at time " + newEvent.getTime());
                int pos = -1;
                boolean foundLarger = false;
                Iterator<SimulationEvent> iter = this.queue.iterator();
                while (iter.hasNext() && !foundLarger) {
                    ++pos;
                    SimulationEvent check = iter.next();
                    foundLarger = check.getTime() > newEvent.getTime();
                }
                System.out.println("   pos is " + pos);
                System.out.println("   foundLarger is " + foundLarger);
                if (!foundLarger) {
                    this.queue.add(newEvent);
                    System.out.println("   added to end ");
                } else {
                    this.queue.add(pos, newEvent);
                    System.out.println("   added to position " + pos);
                }
            }
        }
    }

    @Override
    public SimulationEvent peek() {
        SimulationEvent front = null;
        if (this.isEmpty()) {
            throw new EmptyQueueException("Attempting to access entries on an empty queue.");
        }
        front = this.queue.get(0);
        return front;
    }

    @Override
    public SimulationEvent remove() {
        SimulationEvent front = null;
        if (this.isEmpty()) {
            throw new EmptyQueueException("Attempting to access entries on an empty queue.");
        }
        front = this.queue.get(0);
        this.queue.remove(0);
        this.theCurrentTime = front.getTime();
        return front;
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public void clear() {
        this.queue.clear();
    }

    @Override
    public int getSize() {
        return this.queue.size();
    }

    @Override
    public double getCurrentTime() {
        return this.theCurrentTime;
    }
}

