package de.sfn_kassel.FourierPaint;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage inImg = ImageIO.read(new File("/home/robin/TestFFT.png"));

        FourierTransformation ft = new FourierTransformation(inImg.getWidth(), inImg.getHeight());

        //ArrayList<Complex> result = ft.execute(new double[]{1, 1, 1, 1});
        double in[] = new double[inImg.getWidth() * inImg.getHeight()];

        for (int i = 0; i < in.length; i++) {
            int x = i / (inImg.getWidth());
            int y = i % (inImg.getHeight());

            in[i] = rgbToGrayscale(inImg.getRGB(x, y));
        }

        /*for (int i = 0; i < 1024; i++) {
            in[i] = Math.sin(((2 * Math.PI) / 1024) * i);

            in[i] += rand.nextDouble() * 0.02 - 0.01;
        }*/

        //ft = new FourierTransformation(256);

        // in = new double[256];

        //for (int i = 0; i < 256; i++) {
        //  in[i] = ((i % 8) - 4) > 0 ? 1 : -1;
        //}

        ArrayList<Complex> result = ft.execute(in);

        double a = 0;
        double b = 0;

        for(Complex c : result) {
            a += c.real;
            b += c.imaginary;

            System.out.println("total real: " + c.real + " total imaginary: " + c.imaginary);
        }

        System.out.println("total real: " + a + " total imaginary: " + b);
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

