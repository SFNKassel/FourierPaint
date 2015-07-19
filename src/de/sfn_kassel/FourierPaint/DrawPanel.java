package de.sfn_kassel.FourierPaint;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by robin on 19.07.15.
 */
public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    private BufferedImage img;
    private IBrush brush;
    private boolean mousePressed;
    private int prevX = 0;
    private int prevY = 0;

    public DrawPanel() {
        super();

        this.setBorder(new BevelBorder(BevelBorder.LOWERED));

        img = new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
        img.createGraphics().setColor(Color.white);
        img.createGraphics().fillRect(0, 0, 800, 600);

        brush = new Pencil(10);

        mousePressed = false;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        brush.applyTo(img, e.getX(), e.getY());
        prevX = e.getX();
        prevY = e.getY();
        this.updateUI();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        brush.applyTo(img, prevX, prevY, e.getX(), e.getY());
        prevX = e.getX();
        prevY = e.getY();
        this.updateUI();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mousePressed) {
            brush.applyTo(img, prevX, prevY, e.getX(), e.getY());
            prevX = e.getX();
            prevY = e.getY();
            this.updateUI();
        }
    }
}
