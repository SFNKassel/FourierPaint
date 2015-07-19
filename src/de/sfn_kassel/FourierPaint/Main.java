package de.sfn_kassel.FourierPaint;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new FourierPaint();
    }

    public static double rgbToGrayscale(int i) {
        double grey;

        double red = (i & 0x000000FF) / 255;
        double green = (i & 0x0000FF00) / 255 / 256;
        double blue = (i & 0x00FF0000) / 255 / 256 / 256;

        grey = (red + green + blue) / 3;

        return (grey * 2) - 1;
    }
}

