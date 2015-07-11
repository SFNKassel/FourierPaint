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

    public ArrayList<Complex> execute(double[] in) {
        this.in.rewind();
        this.in.put(in);
        //fftw.fftw_execute_dft_r2c(plan, this.in, this.out);

        fftw.fftw_execute(plan);

        ArrayList<Complex> result = new ArrayList<Complex>();

        for (int i = 0; i < totalSize + 2; i += 2) {
            result.add(new Complex(out.getDoubleArray(i * 8, 2)));
        }

        return result;
    }
}
