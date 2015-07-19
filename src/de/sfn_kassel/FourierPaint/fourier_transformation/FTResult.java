package de.sfn_kassel.FourierPaint.fourier_transformation;

import java.util.ArrayList;

/**
 * Created by robin on 19.07.15.
 */
public class FTResult extends ArrayList<Sinus> {
    public FTResult(ArrayList<Complex> in, double samplerate, int size) {
        Complex c;
        double phi;

        for (int i = 0; i < in.size(); i++) {
            c = in.get(i);

            phi = Math.atan(c.real / c.imaginary);

            this.add(new Sinus(c.real / phi, phi, i * samplerate / size));
        }
    }
}
