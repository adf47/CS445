/*
 * Decompiled with CFR 0_114.
 */
package QueuePackage;

import QueuePackage.EmptyQueueException;
import QueuePackage.QueueInterface;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class VectorQueue<T>
implements QueueInterface<T>,
Serializable {
    private List<T> queue = new Vector<T>();

    @Override
    public void enqueue(T newEntry) {
        this.queue.add(newEntry);
    }

    @Override
    public T dequeue() {
        T front = null;
        if (this.isEmpty()) {
            throw new EmptyQueueException("Attempting to access entries on an empty queue.");
        }
        front = this.queue.remove(0);
        return front;
    }

    @Override
    public T getFront() {
        T front = null;
        if (this.isEmpty()) {
            throw new EmptyQueueException("Attempting to access entries on an empty queue.");
        }
        front = this.queue.get(0);
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
}

