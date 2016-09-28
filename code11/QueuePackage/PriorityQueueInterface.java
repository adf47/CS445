/*
 * Decompiled with CFR 0_114.
 */
package QueuePackage;

public interface PriorityQueueInterface<T extends Comparable<? super T>> {
    public void add(T var1);

    public T remove();

    public T peek();

    public boolean isEmpty();

    public int getSize();

    public void clear();
}

