package de.sfn_kassel.FourierPaint;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.sun.jna.Pointer;
import fftw3.Fftw3Library;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static fftw3.Fftw3Library.FFTW_ESTIMATE;

public class Main {

    public static void main(String[] args) {
        FourierTransformation ft = new FourierTransformation(4);

        ArrayList<Complex> result = ft.execute(new double[]{0, 1, 1, 0});

        for(Complex c : result) {
            System.out.println("real: " + c.real + " imaginary: " + c.imaginary);
        }
    }
}
