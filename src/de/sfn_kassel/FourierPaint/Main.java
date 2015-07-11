package de.sfn_kassel.FourierPaint;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.sun.jna.Pointer;
import fftw3.Fftw3Library;

import java.nio.DoubleBuffer;

import static fftw3.Fftw3Library.FFTW_ESTIMATE;

public class Main {

    public static void main(String[] args) {
        Fftw3Library fft = Fftw3Library.INSTANCE;

        DoubleBuffer in = fft.fftw_malloc(new NativeSize(8 * 128)).getByteBuffer(0, 128 * 8).asDoubleBuffer();
        Pointer out = fft.fftw_malloc(new NativeSize(8 * 130));
        Fftw3Library.fftw_plan plan = fft.fftw_plan_dft_r2c_1d(4, in, out, FFTW_ESTIMATE);

        out.setMemory(0, 128 * 8, (byte) 0);

        in.put(new double[]{1, 1, 1, 1});
        //in.put(new double[]{0.d, 0.38d, 0.707d, 0.92d, 1.d, 0.92d, 0.707d, 0.38d, 0, -0.38d, -0.707d, -0.92d, -1.d, -0.92d, -0.707d, -0.38d, 0});
        fft.fftw_execute(plan);

        for (int i = 0; i < 32; i += 2) {
            System.out.println("real: " + out.getDoubleArray(0, 32)[i] + " complex: " + out.getDoubleArray(0, 32)[i + 1]);
        }
    }
}
