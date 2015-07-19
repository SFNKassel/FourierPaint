package de.sfn_kassel.FourierPaint.fourier_transformation;

/**
 * Created by robin on 19.07.15.
 */
public class Sinus {
    private double amplitude;
    private double phi;
    private double freq;

    public Sinus() {
        this(1, 0, 1);
    }

    public Sinus(double amplitude, double phi, double freq) {
        this.amplitude = amplitude;
        this.phi = phi;
        this.freq = freq;
    }

    public double valueAt(double x) {
        return amplitude * Math.sin(freq * x + phi);
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }
}
