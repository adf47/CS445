/*
 * Decompiled with CFR 0_114.
 */
package QueuePackage;

import QueuePackage.SimulationEventInterface;

public abstract class SimulationEvent
implements SimulationEventInterface {
    private double atTime;
    private String description;
    protected String postActionReport;

    public SimulationEvent(double theTime, String action) {
        this.atTime = theTime;
        this.description = action;
        this.postActionReport = "Event has not fired yet";
    }

    @Override
    public double getTime() {
        return this.atTime;
    }

    @Override
    public abstract void process();

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getPostActionReport() {
        return this.postActionReport;
    }
}

