package de.sfn_kassel.FourierPaint;

import javax.swing.*;
import java.awt.*;

/**
 * Created by robin on 19.07.15.
 */
public class FourierPaint extends JFrame {
    private DrawPanel fourierPanel;
    private DrawPanel picturePanel;
    private MenuPanel menuPanel;

    public FourierPaint() {
        super();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        Container cp = this.getContentPane();

        cp.setLayout(new BorderLayout());

        JPanel drawPanel = new JPanel();

        drawPanel.setLayout(new BoxLayout(drawPanel, BoxLayout.LINE_AXIS));

        fourierPanel = new DrawPanel(256);
        picturePanel = new DrawPanel(256);
        menuPanel = new MenuPanel();

        fourierPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        picturePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cp.add(menuPanel, BorderLayout.PAGE_START);
        cp.add(drawPanel, BorderLayout.CENTER);

        drawPanel.add(fourierPanel);
        drawPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        drawPanel.add(picturePanel);

        this.setTitle("FourierPaint");

        this.setVisible(true);
    }
}
