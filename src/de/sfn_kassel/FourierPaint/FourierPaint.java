package de.sfn_kassel.FourierPaint;

import javax.swing.*;
import java.awt.*;

/**
 * Created by robin on 19.07.15.
 */
public class FourierPaint extends JFrame {
    private DrawPanel fourier;

    public FourierPaint() {
        super();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        Container cp = this.getContentPane();

        fourier = new DrawPanel();

        cp.add(fourier);

        this.setTitle("FourierPaint");

        this.setVisible(true);
    }
}
