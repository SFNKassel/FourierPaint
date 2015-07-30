package de.sfn_kassel.FourierPaint;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by robin on 19.07.15.
 */
public class Pencil implements IBrush {
    private int size;
    private Color color;

    public Pencil() {
        this(1);
    }

    public Pencil(int i) {
        this.size = i;
        this.color = Color.black;
    }

    @Override
    public void applyTo(BufferedImage img, int x, int y) {
        Graphics2D g = img.createGraphics();

        g.setColor(this.color);

        g.fillOval(x - (size / 2), y - (size / 2), size, size);
    }

    @Override
    public void applyTo(BufferedImage img, int x1, int y1, int x2, int y2) {
        boolean x = Math.abs(x2 - x1) > Math.abs(y2 - y1);
        int k = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));

        if (x) {
            if (x1 < x2) {
                for (int i = x1; i <= x2; i++) {
                    this.applyTo(img, i, y1 + (int) (((double) (y2 - y1) / (double) k) * (double) (i - x1)));
                }
            } else {
                for (int i = x2; i <= x1; i++) {
                    this.applyTo(img, i, y2 + (int) (((double) (y1 - y2) / (double) k) * (double) (i - x2)));
                }
            }
        } else {
            if (y1 < y2) {
                for (int i = y1; i <= y2; i++) {
                    this.applyTo(img, x1 + (int) (((double) (x2 - x1) / (double) k) * (double) (i - y1)), i);
                }
            } else {
                for (int i = y2; i <= y1; i++) {
                    this.applyTo(img, x2 + (int) (((double) (x1 - x2) / (double) k) * (double) (i - y2)), i);
                }
            }
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }
}
