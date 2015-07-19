package de.sfn_kassel.FourierPaint;

import de.sfn_kassel.FourierPaint.fourier_transformation.Complex;
import de.sfn_kassel.FourierPaint.fourier_transformation.FourierTransformation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage inImg = ImageIO.read(new File("/home/robin/TestFFT.png"));

        FourierTransformation ft = new FourierTransformation(inImg.getWidth(), inImg.getHeight());

        double in[] = new double[inImg.getWidth() * inImg.getHeight()];

        for (int i = 0; i < in.length; i++) {
            int x = i / (inImg.getWidth());
            int y = i % (inImg.getHeight());

            in[i] = rgbToGrayscale(inImg.getRGB(x, y));
        }

        ArrayList<Complex> result = ft.executeForward(in);

        double a = 0;
        double b = 0;

        for(Complex c : result) {
            a += c.real;
            b += c.imaginary;

            System.out.println("real: " + c.real + " imaginary: " + c.imaginary);
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

