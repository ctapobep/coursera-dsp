package coursera.dsp.dft;

public class FourierTransform {
    public float[] analyze(float[] signal) {
        FourierBasis basis = new FourierBasis(signal.length);
        return basis.notNormalizedInnerProduct(signal);
    }
    public float[] synthesize(float[] frequencies) {
        FourierBasis basis = new FourierBasis(frequencies.length);
        return basis.linearlyCombine(frequencies);
    }
}
