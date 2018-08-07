package coursera.dsp.wavelets;

import static java.lang.Math.pow;

public class HaarWavelet {
    private final HaarWaveletMother mother = new HaarWaveletMother();
    private final int translation, scale;

    public HaarWavelet(int translation, int scale) {
        this.translation = translation;
        this.scale = scale;
    }

    public float[] get(float[] x) {
        float[] result = new float[x.length];
        for(int i = 0; i < x.length; i++)
            result[i] = get(x[i]);
        return result;
    }
    public float get(float x) {
        return mother.get((float) ((x - pow(2, scale) * translation) / pow(2, scale)));
    }
}
