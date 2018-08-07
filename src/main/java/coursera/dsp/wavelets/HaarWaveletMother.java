package coursera.dsp.wavelets;

public class HaarWaveletMother {
    public float get(float x) {
        if(x >= 0 && x < .5)
            return -1;
        if(x >= .5 && x < 1)
            return 1;
        return 0;
    }
}
