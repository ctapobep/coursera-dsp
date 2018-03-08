package coursera.dsp.signal;

public class FiniteSignal implements Signal {
    private final float[] data;

    public FiniteSignal(float[] data) {
        this.data = data;
    }

    @Override public float get(int idx) {
        return data[idx];
    }

    float[] getData() {
        return data;
    }

    public static FiniteSignal delta(int size) {
        float[] result = new float[size];
        result[0] = 1;
        return new FiniteSignal(result);
    }
}
