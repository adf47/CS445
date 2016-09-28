/*
 * Decompiled with CFR 0_114.
 */
import javax.swing.JPanel;

abstract class ActionThread
extends Thread {
    private boolean resetApplicationInThread;
    private boolean killTheThread;
    private boolean changesAllowed;
    private Stepper myStepper;
    private JPanel myPanel;

    abstract JPanel createAnimationPanel();

    abstract void setUpApplicationSpecificControls();

    abstract void init();

    abstract void executeApplication();

    abstract String getApplicationTitle();

    public ActionThread() {
        this.myPanel = this.createAnimationPanel();
        this.resetApplicationInThread = false;
        this.killTheThread = false;
        this.changesAllowed = true;
        this.setUpApplicationSpecificControls();
    }

    public final void animationPause() {
        this.myStepper.animationStep();
        this.makeThreadWellBehaved();
    }

    public final void forceLastPause() {
        this.myStepper.finalStep();
        this.makeThreadWellBehaved();
    }

    public final void makeThreadWellBehaved() {
        if (this.killTheThread) {
            throw new KillThreadException("Thread is being killed");
        }
        if (this.resetApplicationInThread) {
            throw new ResetApplicationException("Application is being reset");
        }
    }

    public final JPanel getAnimationPanel() {
        return this.myPanel;
    }

    public final boolean applicationControlsAreActive() {
        return this.changesAllowed;
    }

    public final void setStepper(Stepper s) {
        this.myStepper = s;
    }

    public final void resetExecution() {
        this.resetApplicationInThread = true;
    }

    public final void killThread() {
        this.killTheThread = true;
    }

    @Override
    public final void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            this.init();
            this.changesAllowed = true;
            this.myStepper.setupStep();
            this.changesAllowed = false;
            try {
                this.init();
                this.myStepper.initialStateStep();
                this.makeThreadWellBehaved();
                this.executeApplication();
                this.myStepper.finalStep();
            }
            catch (KillThreadException e) {
                keepGoing = false;
            }
            catch (ResetApplicationException e) {
                // empty catch block
            }
            this.resetApplicationInThread = false;
        }
    }
}

