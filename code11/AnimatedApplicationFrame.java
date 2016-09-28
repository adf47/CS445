/*
 * Decompiled with CFR 0_114.
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public final class AnimatedApplicationFrame
extends JFrame {
    public static final int INITIAL_DELAY = 100;
    private Object dispatcher;
    private Stepper myStepper;
    private Timer myTimer;
    private ActionThread myThread;
    private static final int CONTROL_HEIGHT = 160;
    private static final int CONTROL_WIDTH = 400;
    private JButton resetButton;
    private JButton goButton;
    private JButton pauseButton;
    private JButton stepButton;
    private JLabel statusLabel;
    private JLabel delayLabel;
    private JLabel delayFeedbackLabel;
    private JTextField delayTextField;
    private JPanel buttonPanel;
    private JPanel delayPanel;
    private JPanel controlPanel;
    private JPanel drawingPanel;

    public AnimatedApplicationFrame(String title, Object d, JPanel dPanel, ActionThread t) {
        this.dispatcher = d;
        this.myStepper = new Stepper(this.dispatcher, this);
        this.drawingPanel = dPanel;
        this.myThread = t;
        int apWidth = this.drawingPanel.getWidth();
        int apHeight = this.drawingPanel.getHeight();
        this.createControls();
        this.startControls();
        this.setControlActions();
        if (apWidth < 400) {
            apWidth = 400;
        }
        this.setWindow(apWidth, apHeight + 160, title);
        this.setUpTimer();
        this.repaint();
    }

    private void setUpTimer() {
        this.myTimer = new Timer(1000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                AnimatedApplicationFrame.this.doStep();
            }
        });
    }

    private void createControls() {
        this.resetButton = new JButton("Reset");
        this.goButton = new JButton("Go");
        this.pauseButton = new JButton("Pause");
        this.stepButton = new JButton("Step");
        this.statusLabel = new JLabel("Setup Phase");
        this.delayFeedbackLabel = new JLabel("");
        this.delayLabel = new JLabel("Step delay (units of 0.01 second) ");
        this.delayLabel.setHorizontalAlignment(4);
        this.delayTextField = new JTextField(new Integer(100).toString());
        this.controlPanel = new JPanel();
        this.buttonPanel = new JPanel();
        this.delayPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(1, 4, 10, 1));
        this.buttonPanel.add(this.resetButton);
        this.buttonPanel.add(this.goButton);
        this.buttonPanel.add(this.pauseButton);
        this.buttonPanel.add(this.stepButton);
        this.delayPanel.setLayout(new GridLayout(1, 2, 5, 1));
        this.delayPanel.add(this.delayLabel);
        this.delayPanel.add(this.delayTextField);
        this.controlPanel.setLayout(new GridLayout(4, 1, 1, 10));
        this.controlPanel.add(this.buttonPanel);
        this.controlPanel.add(this.delayPanel);
        this.controlPanel.add(this.delayFeedbackLabel);
        this.controlPanel.add(this.statusLabel);
        this.delayFeedbackLabel.setText("Delay is 1.0 seconds");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add((Component)this.controlPanel, "North");
        this.getContentPane().add((Component)this.drawingPanel, "Center");
    }

    private void setControlActions() {
        this.resetButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                AnimatedApplicationFrame.this.resetButtonHandler();
            }
        });
        this.goButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                AnimatedApplicationFrame.this.goButtonHandler();
            }
        });
        this.pauseButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                AnimatedApplicationFrame.this.pauseButtonHandler();
            }
        });
        this.stepButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                AnimatedApplicationFrame.this.stepButtonHandler();
            }
        });
        this.delayTextField.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent event) {
                AnimatedApplicationFrame.this.delayTextFieldHandler();
            }
        });
    }

    private void resetButtonHandler() {
        this.myTimer.stop();
        this.startControls();
        this.myThread.resetExecution();
        Object object = this.dispatcher;
        synchronized (object) {
            this.dispatcher.notify();
        }
    }

    private void goButtonHandler() {
        this.myTimer.start();
        this.goControls();
        this.statusLabel.setText("Started");
        this.repaint();
    }

    private void pauseButtonHandler() {
        this.myTimer.stop();
        this.stepControls();
        this.statusLabel.setText("Paused");
        this.repaint();
    }

    private void stepButtonHandler() {
        this.stepControls();
        this.doStep();
    }

    private void doStep() {
        Object object = this.dispatcher;
        synchronized (object) {
            this.dispatcher.notify();
        }
    }

    private void delayTextFieldHandler() {
        try {
            String input = this.delayTextField.getText().trim();
            int newDelay = Integer.parseInt(input);
            this.myTimer.setDelay(newDelay * 10);
            double delayInSeconds = (double)newDelay / 100.0;
            if (newDelay < 10) {
                this.delayFeedbackLabel.setText("Delay is " + delayInSeconds + " seconds: Warning not recommended");
            } else {
                this.delayFeedbackLabel.setText("Delay is " + delayInSeconds + " seconds");
            }
            this.repaint();
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    private void startControls() {
        this.goButton.setEnabled(true);
        this.pauseButton.setEnabled(false);
        this.stepButton.setEnabled(true);
        this.resetButton.setEnabled(false);
    }

    private void stepControls() {
        this.goButton.setEnabled(true);
        this.pauseButton.setEnabled(false);
        this.stepButton.setEnabled(true);
        this.resetButton.setEnabled(true);
    }

    private void goControls() {
        this.goButton.setEnabled(false);
        this.pauseButton.setEnabled(true);
        this.stepButton.setEnabled(false);
        this.resetButton.setEnabled(true);
    }

    private void finalControls() {
        this.goButton.setEnabled(false);
        this.pauseButton.setEnabled(false);
        this.stepButton.setEnabled(false);
        this.resetButton.setEnabled(true);
    }

    public Stepper getStepper() {
        return this.myStepper;
    }

    @Override
    public void paint(Graphics g) {
        Stepper.State state = this.myStepper.getStatus();
        switch (state) {
            case SETUP: {
                this.statusLabel.setText("Setup Phase");
                break;
            }
            case INITIAL: {
                this.statusLabel.setText("Inital State");
                break;
            }
            case STEPPING: {
                this.statusLabel.setText("Step " + this.myStepper.getStep());
                break;
            }
            case FINAL: {
                this.myTimer.stop();
                this.statusLabel.setText("Finished");
                this.finalControls();
            }
        }
        super.paint(g);
    }

    private void setWindow(int width, int height, String title) {
        this.setTitle(title);
        this.setLocation(0, 0);
        this.setSize(width, height);
        this.setVisible(true);
        WindowDestroyer myClose = new WindowDestroyer();
        this.addWindowListener(myClose);
    }

    private class WindowDestroyer
    extends WindowAdapter {
        private WindowDestroyer() {
        }

        @Override
        public void windowClosing(WindowEvent event) {
            if (AnimatedApplicationFrame.this.myThread != null) {
                AnimatedApplicationFrame.this.myThread.killThread();
            }
            System.exit(0);
        }
    }

}

