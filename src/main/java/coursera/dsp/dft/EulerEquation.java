package coursera.dsp.dft;

public class EulerEquation {
    public static EulerEquation ZERO = new EulerEquation(0);
    private final float piMultiple;

    public EulerEquation(float piMultiple) {
        this.piMultiple = piMultiple;
    }
    public static EulerEquation eip(float piMultiple) {
        return new EulerEquation(piMultiple);
    }

    public float getRe() {
        return (float) Math.cos(Math.PI * piMultiple);
    }
    public float getIm() {
        return (float) Math.sin(Math.PI * piMultiple);
    }
    public float getPiMultiple() {
        return piMultiple;
    }
    public EulerEquation conjugate() {
        return new EulerEquation(-1 * piMultiple);
    }

    public EulerEquation multiply(EulerEquation another) {
        return new EulerEquation(this.piMultiple + another.piMultiple);
    }

    @Override public String toString() {
        return "e^i*" + piMultiple +"*π";
    }
}
