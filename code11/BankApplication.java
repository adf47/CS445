/*
 * Decompiled with CFR 0_114.
 */
import javax.swing.JPanel;

public class BankApplication {
    public static void main(String[] args) {
        BankActionThread myThread = new BankActionThread();
        JPanel myPanel = myThread.getAnimationPanel();
        Object dispatcher = new Object();
        AnimatedApplicationFrame myFrame = new AnimatedApplicationFrame(myThread.getApplicationTitle(), dispatcher, myPanel, myThread);
        Stepper myStepper = myFrame.getStepper();
        myThread.setStepper(myStepper);
        myThread.start();
    }
}

