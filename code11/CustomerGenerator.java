/*
 * Decompiled with CFR 0_114.
 */
import QueuePackage.SimulationEvent;
import java.util.Random;

public class CustomerGenerator {
    private int maxForNext;
    private SimulationEventQueue theEventQueue;
    private Random sharedRandomGenerator;
    private String lastNameWas;
    private BankLine theLine;
    public int count;

    public CustomerGenerator(int maxTimeToNextCustomer, SimulationEventQueue simulationEventQueue, Random simulationRandomGenerator, BankLine putInLine) {
        this.maxForNext = maxTimeToNextCustomer;
        this.theEventQueue = simulationEventQueue;
        this.sharedRandomGenerator = simulationRandomGenerator;
        this.theLine = putInLine;
        this.lastNameWas = "";
        this.count = 0;
        int timeToNext = this.sharedRandomGenerator.nextInt(this.maxForNext);
        GenerateCustomerEvent nextGeneration = new GenerateCustomerEvent(this.theEventQueue.getCurrentTime() + (double)timeToNext, "Generate the first customer.");
        this.theEventQueue.add(nextGeneration);
    }

    private String getName() {
        ++this.count;
        this.lastNameWas = Integer.toString(this.count);
        return this.lastNameWas;
    }

    public class GenerateCustomerEvent
    extends SimulationEvent {
        public GenerateCustomerEvent(double theTime, String action) {
            super(theTime, action);
        }

        @Override
        public void process() {
            Customer generated = new Customer(CustomerGenerator.this.getName(), CustomerGenerator.this.theEventQueue.getCurrentTime());
            CustomerGenerator.this.theLine.enqueue(generated);
            this.postActionReport = "Generated customer " + CustomerGenerator.this.lastNameWas;
            int timeToNext = CustomerGenerator.this.sharedRandomGenerator.nextInt(CustomerGenerator.this.maxForNext);
            GenerateCustomerEvent nextGeneration = new GenerateCustomerEvent(CustomerGenerator.this.theEventQueue.getCurrentTime() + (double)timeToNext, "Generate the next customer");
            CustomerGenerator.this.theEventQueue.add(nextGeneration);
        }
    }

}

