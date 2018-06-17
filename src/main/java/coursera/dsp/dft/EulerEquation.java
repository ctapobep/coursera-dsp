package coursera.dsp.dft;

public class EulerEquation {
    private final float piMultiple;
    private final int imSign;

    public EulerEquation(float piMultiple) {
        this(piMultiple, 1);
    }
    public EulerEquation(float piMultiple, int imSign) {
        this.piMultiple = piMultiple;
        this.imSign = imSign;
    }

    public float getRe() {
        return (float) Math.cos(Math.PI * piMultiple);
    }
    public float getIm() {
        return (float) Math.sin(Math.PI * piMultiple) * imSign;
    }
    public float getPiMultiple() {
        return piMultiple;
    }
    public EulerEquation getConjugate() {
        return new EulerEquation(piMultiple, -1 * imSign);
    }

    @Override public String toString() {
        return "e^" + imSign + "*" + piMultiple +"*π";
    }
}
