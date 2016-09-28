/*
 * Decompiled with CFR 0_114.
 */
package QueuePackage;

import QueuePackage.SimulationEvent;

public interface SimulationEventQueueInterface {
    public void add(SimulationEvent var1);

    public SimulationEvent remove();

    public SimulationEvent peek();

    public boolean isEmpty();

    public int getSize();

    public void clear();

    public double getCurrentTime();
}

