package de.sfn_kassel.FourierPaint;

import com.ochafik.lang.jnaerator.runtime.NativeSize;
import com.sun.jna.Pointer;
import fftw3.Fftw3Library;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

/**
 * Created by robin on 11.07.15.
 */
public class FourierTransformation {
    private Fftw3Library.fftw_plan plan;
    private Fftw3Library fftw = Fftw3Library.INSTANCE;
    private DoubleBuffer in;
    private Pointer out;
    private int totalSize;

    public FourierTransformation(int x) {
        in = fftw.fftw_malloc(new NativeSize(8 * x + 2)).getByteBuffer(0, 8 * x + 2).asDoubleBuffer();
        out = fftw.fftw_malloc(new NativeSize(8 * x + 2));
        out.setMemory(0, 8 * x, (byte) 0);

        totalSize = x;

        plan = fftw.fftw_plan_dft_r2c_1d(x, in, out, Fftw3Library.FFTW_ESTIMATE);
    }

    public FourierTransformation(int x, int y) {
        in = fftw.fftw_malloc(new NativeSize(8 * (x + 2) * y)).getByteBuffer(0, 8 * (x + 2) * y).asDoubleBuffer();
        out = fftw.fftw_malloc(new NativeSize(8 * (x + 2) * y));
        out.setMemory(0, 8 * (x + 2) * y, (byte) 0);

        totalSize = x * y;

        plan = fftw.fftw_plan_dft_r2c_2d(x, y, in, out, Fftw3Library.FFTW_ESTIMATE);
    }

    public ArrayList<Complex> execute(double[] in) {
        this.in.rewind();
        this.in.put(in);
        //fftw.fftw_execute_dft_r2c(plan, this.in, this.out);

        fftw.fftw_execute(plan);

        ArrayList<Complex> result = new ArrayList<>();

        for (int i = 0; i < 2 * totalSize; i += 2) {
            result.add(new Complex(out.getDoubleArray(i * 8, 2)[0] / totalSize, out.getDoubleArray(i * 8, 2)[1] / totalSize));
        }

        return result;
    }
}
