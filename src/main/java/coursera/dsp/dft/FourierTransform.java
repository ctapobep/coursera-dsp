package coursera.dsp.dft;

public class FourierTransform {
    public float[] analyze(float[] signal) {
        FourierBasis basis = new FourierBasis(signal.length);
        return basis.innerProduct(signal);
    }
}
