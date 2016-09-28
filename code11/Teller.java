/*
 * Decompiled with CFR 0_114.
 */
import QueuePackage.SimulationEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JOptionPane;

public class Teller {
    private String myName;
    private Customer serving;
    private int maxForHelp;
    private SimulationEventQueue theEventQueue;
    private Random sharedRandomGenerator;
    private String lastNameWas;
    private BankLine theLine;
    private Report reportServices;
    private static boolean firstUse = true;
    public static final int LINE_HEIGHT = 15;
    public static final int TORSO_HEIGHT = 18;
    public static final int NECK_HEIGHT = 3;
    public static final int LEG_HEIGHT = 18;
    public static final int ARM_WIDTH = 13;
    public static final int HEAD_SIZE = 6;
    public static final int BARRIER_SIZE = 4;

    public Teller(String name, int maxTimeToHelpCustomer, SimulationEventQueue simulationEventQueue, Random simulationRandomGenerator, BankLine servicesLine, Report toReportTo) {
        this.myName = name;
        this.serving = null;
        this.maxForHelp = maxTimeToHelpCustomer;
        this.theEventQueue = simulationEventQueue;
        this.sharedRandomGenerator = simulationRandomGenerator;
        this.theLine = servicesLine;
        this.reportServices = toReportTo;
        CheckForCustomerEvent nextCheck = new CheckForCustomerEvent(this.theEventQueue.getCurrentTime(), "Start checking the line for customers");
        this.theEventQueue.add(nextCheck);
        if (firstUse) {
            JOptionPane.showMessageDialog(null, "Sample application simulating a line at a bank using a queue.");
            firstUse = false;
        }
    }

    public synchronized void serve(Customer c) {
        this.serving = c;
        c.servedAt(this.theEventQueue.getCurrentTime());
        this.reportServices.addServed(c);
    }

    public synchronized void drawOn(Graphics g, int leftX, int baseY) {
        g.setColor(Color.blue);
        int totalHeight = 51;
        int totalWidth = 26;
        int headX = leftX + 13 - 6;
        int headY = baseY - totalHeight;
        g.fillOval(headX, headY, 12, 12);
        int bodyX = leftX + 13;
        int topBodyY = baseY - totalHeight + 12;
        int bottomBodyY = baseY - 18;
        g.drawLine(bodyX, topBodyY, bodyX, bottomBodyY);
        int leftArmX = leftX;
        int rightArmX = leftX + 26;
        int armY = baseY - 18 - 18;
        g.drawLine(leftArmX, armY, rightArmX, armY);
        int topLegX = leftX + 13;
        int topLegY = baseY - 18;
        int leftLegBottomX = leftX;
        int leftLegBottomY = baseY;
        int rightLegBottomX = leftX + 26;
        int rightLegBottomY = baseY;
        g.drawLine(topLegX, topLegY, leftLegBottomX, leftLegBottomY);
        g.drawLine(topLegX, topLegY, rightLegBottomX, rightLegBottomY);
        if (this.myName != null) {
            g.drawString(this.myName, leftX, baseY + 15);
        }
        g.setColor(Color.black);
        int leftBarrierX = leftX + totalWidth + 4;
        int topBarrierY = baseY - 18 - 18;
        g.fillRect(leftBarrierX, topBarrierY, 8, 4);
        leftBarrierX = leftX + totalWidth + 8;
        g.fillRect(leftBarrierX, topBarrierY, 4, 36);
        int serveX = leftX + totalWidth + 16;
        if (this.serving != null) {
            this.serving.drawOn(g, serveX, baseY);
        }
    }

    public class CheckForCustomerEvent
    extends SimulationEvent {
        public CheckForCustomerEvent(double theTime, String action) {
            super(theTime, action);
        }

        @Override
        public synchronized void process() {
            if (Teller.this.theLine.isEmpty()) {
                CheckForCustomerEvent nextCheck = new CheckForCustomerEvent(Teller.this.theEventQueue.getCurrentTime() + 1.0, "Check line for customer");
                Teller.this.theEventQueue.add(nextCheck);
                Teller.this.serving = null;
                this.postActionReport = "Checked for customer, line was empty";
            } else {
                Customer toHelp = Teller.this.theLine.dequeue();
                Teller.this.serve(toHelp);
                int timeToNext = Teller.this.sharedRandomGenerator.nextInt(Teller.this.maxForHelp);
                CheckForCustomerEvent nextGeneration = new CheckForCustomerEvent(Teller.this.theEventQueue.getCurrentTime() + (double)timeToNext, "After helping customer " + toHelp.getName() + " , check for next customer");
                Teller.this.theEventQueue.add(nextGeneration);
                this.postActionReport = "Checked for customer, helping customer";
            }
        }
    }

}

