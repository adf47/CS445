/*
 * Decompiled with CFR 0_114.
 */
import QueuePackage.SimulationEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BankActionThread
extends ActionThread {
    private int maxForService = 20;
    private int maxForInterval = 20;
    private int stopSimulationAt = 1000;
    private BankLine myLine;
    private Teller myTeller;
    private String nextEventAction;
    private String lastEventReport;
    private Report myReport;
    private SimulationEventQueue theEvents;
    private CustomerGenerator myCG;
    private Random sharedRandom;
    private static int DISPLAY_HEIGHT = 500;
    private static int DISPLAY_WIDTH = 500;
    private static int LINE_OFFSET_X = 50;
    private static int LINE_Y_TO_BASE = 100;
    private static int TELLER_Y_TO_BASE = 200;
    private static int REPORT_Y_TO_TOP = 250;
    private JTextField maxIntervalTextField;
    private JTextField maxServiceTextField;
    private JTextField endSimulationTextField;
    private JLabel setupStatusLabel;
    private JPanel setupPanel;

    @Override
    public String getApplicationTitle() {
        return "Bank Simulation (Sample Application)";
    }

    @Override
    public void init() {
        this.theEvents = new SimulationEventQueue();
        this.sharedRandom = new Random();
        this.myLine = new BankLine();
        this.myReport = new Report(this.myLine);
        this.myTeller = new Teller("Fred", this.maxForService, this.theEvents, this.sharedRandom, this.myLine, this.myReport);
        this.myCG = new CustomerGenerator(this.maxForInterval, this.theEvents, this.sharedRandom, this.myLine);
        this.lastEventReport = "No events have been processed";
        SimulationEvent next = this.theEvents.peek();
        this.nextEventAction = next != null ? next.getDescription() : "No events to process";
    }

    @Override
    public void executeApplication() {
        while (!this.theEvents.isEmpty() && this.theEvents.getCurrentTime() < (double)this.stopSimulationAt) {
            SimulationEvent front = this.theEvents.remove();
            front.process();
            this.lastEventReport = front.getPostActionReport();
            SimulationEvent next = this.theEvents.peek();
            this.nextEventAction = next != null ? next.getDescription() : "No events to process yet";
            this.myReport.updateTime(this.theEvents.getCurrentTime());
            this.animationPause();
        }
    }

    @Override
    public JPanel createAnimationPanel() {
        return new AnimationPanel();
    }

    @Override
    public void setUpApplicationSpecificControls() {
        this.getAnimationPanel().setLayout(new BorderLayout());
        this.maxIntervalTextField = new JTextField("20");
        this.maxIntervalTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                BankActionThread.this.maxIntervalTextFieldHandler();
                BankActionThread.this.getAnimationPanel().repaint();
            }
        });
        this.maxServiceTextField = new JTextField("20");
        this.maxServiceTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                BankActionThread.this.maxServiceTextFieldHandler();
                BankActionThread.this.getAnimationPanel().repaint();
            }
        });
        this.endSimulationTextField = new JTextField("1000");
        this.endSimulationTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                BankActionThread.this.endSimulationTextFieldHandler();
                BankActionThread.this.getAnimationPanel().repaint();
            }
        });
        this.setupStatusLabel = new JLabel("");
        this.setupPanel = new JPanel();
        this.setupPanel.setLayout(new GridLayout(4, 2));
        this.setupPanel.add(new JLabel("Max customer interval (integer > 0):"));
        this.setupPanel.add(this.maxIntervalTextField);
        this.setupPanel.add(new JLabel("Max service time (integer > 0):"));
        this.setupPanel.add(this.maxServiceTextField);
        this.setupPanel.add(new JLabel("Max simulation time (integer > 0):"));
        this.setupPanel.add(this.endSimulationTextField);
        this.setupPanel.add(this.setupStatusLabel);
        this.getAnimationPanel().add((Component)this.setupPanel, "South");
    }

    private void maxIntervalTextFieldHandler() {
        try {
            if (this.applicationControlsAreActive()) {
                String input = this.maxIntervalTextField.getText().trim();
                int value = Integer.parseInt(input);
                if (value > 0) {
                    this.maxForInterval = value;
                    this.setupStatusLabel.setText("Maximum customer interval set to " + this.maxForInterval);
                } else {
                    this.setupStatusLabel.setText("Need a positive value for interval ");
                }
            }
        }
        catch (Exception e) {
            this.setupStatusLabel.setText("Need integer value for interval");
        }
    }

    private void maxServiceTextFieldHandler() {
        try {
            if (this.applicationControlsAreActive()) {
                String input = this.maxServiceTextField.getText().trim();
                int value = Integer.parseInt(input);
                if (value > 0) {
                    this.maxForService = value;
                    this.setupStatusLabel.setText("Maximum service time set to " + this.maxForService);
                } else {
                    this.setupStatusLabel.setText("Need a positive value for service ");
                }
            }
        }
        catch (Exception e) {
            this.setupStatusLabel.setText("Need integer value for service");
        }
    }

    private void endSimulationTextFieldHandler() {
        try {
            if (this.applicationControlsAreActive()) {
                String input = this.endSimulationTextField.getText().trim();
                int value = Integer.parseInt(input);
                if (value > 0) {
                    this.stopSimulationAt = value;
                    this.setupStatusLabel.setText("Ending at time " + this.stopSimulationAt);
                } else {
                    this.setupStatusLabel.setText("Need a positive value for stop ");
                }
            }
        }
        catch (Exception e) {
            this.setupStatusLabel.setText("Need integer value for stop");
        }
    }

    public class AnimationPanel
    extends JPanel {
        public AnimationPanel() {
            this.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (BankActionThread.this.lastEventReport != null) {
                g.drawString(BankActionThread.this.lastEventReport, 20, 20);
            }
            if (BankActionThread.this.nextEventAction != null) {
                String toDraw = "Next event: " + BankActionThread.this.nextEventAction;
                g.drawString(toDraw, 20, 35);
            }
            if (BankActionThread.this.myLine != null) {
                BankActionThread.this.myLine.drawOn(g, LINE_OFFSET_X, LINE_Y_TO_BASE);
            }
            if (BankActionThread.this.myTeller != null) {
                BankActionThread.this.myTeller.drawOn(g, 20, TELLER_Y_TO_BASE);
            }
            if (BankActionThread.this.myReport != null) {
                BankActionThread.this.myReport.drawOn(g, 20, REPORT_Y_TO_TOP);
            }
        }
    }

}

