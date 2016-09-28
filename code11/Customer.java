/*
 * Decompiled with CFR 0_114.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Customer {
    private String myName;
    private double arrivedInLine;
    private double leftLine;
    private boolean hasLeftLine;
    public static final int LINE_HEIGHT = 15;
    public static final int TORSO_HEIGHT = 18;
    public static final int NECK_HEIGHT = 3;
    public static final int LEG_HEIGHT = 18;
    public static final int ARM_WIDTH = 13;
    public static final int HEAD_SIZE = 6;

    public Customer(String name, double inTime) {
        this.myName = name;
        this.arrivedInLine = inTime;
        this.leftLine = 0.0;
        this.hasLeftLine = false;
    }

    public void servedAt(double time) {
        this.hasLeftLine = true;
        this.leftLine = time;
    }

    public double arrivalTime() {
        return this.arrivedInLine;
    }

    public double waitTime() {
        return this.leftLine - this.arrivedInLine;
    }

    public String getName() {
        return this.myName;
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
        Font savedFont = g.getFont();
        Font timeFont = new Font("monospaced", 0, 10);
        g.setFont(timeFont);
        String toDraw = new Double(this.arrivedInLine).toString();
        if (this.hasLeftLine) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.red);
        }
        g.drawString(toDraw, leftX, baseY + 30);
        if (this.hasLeftLine) {
            toDraw = new Double(this.leftLine).toString();
            g.setColor(Color.black);
            g.drawString(toDraw, leftX, baseY + 45);
        }
        g.setFont(savedFont);
    }
}

