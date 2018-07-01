package coursera.dsp.dft;

public class FourierTransform {
    public ComplexNumber[] analyze(float[] signal) {
        FourierBasis basis = new FourierBasis(signal.length);
        return basis.innerProduct(signal);
    }
    public ComplexNumber[] synthesize(ComplexNumber[] frequencies) {
        FourierBasis basis = new FourierBasis(frequencies.length);
        return basis.linearlyCombine(frequencies);
    }
}
