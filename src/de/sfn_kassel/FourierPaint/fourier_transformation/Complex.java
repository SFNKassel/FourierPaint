package de.sfn_kassel.FourierPaint.fourier_transformation;

/**
 * Created by robin on 11.07.15.
 */
public class Complex {
    public double real;
    public double imaginary;

    public Complex() {
        this(0, 0);
    }

    public Complex(double i, double i1) {
        real = i;
        imaginary = i1;
    }

    public Complex(double[] i) {
        real = i[0];
        imaginary = i[1];
    }
}
