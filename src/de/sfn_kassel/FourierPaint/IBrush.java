package de.sfn_kassel.FourierPaint;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by robin on 19.07.15.
 */
public interface IBrush {
    void applyTo(BufferedImage img, int x, int y);

    void applyTo(BufferedImage img, int x1, int y1, int x2, int y2);

    int getSize();

    void setSize(int size);

    void setColor(Color c);
}
