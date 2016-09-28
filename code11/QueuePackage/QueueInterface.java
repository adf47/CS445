/*
 * Decompiled with CFR 0_114.
 */
package QueuePackage;

public interface QueueInterface<T> {
    public void enqueue(T var1);

    public T dequeue();

    public T getFront();

    public boolean isEmpty();

    public void clear();
}

