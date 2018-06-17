package coursera.dsp.signal;

public class FiniteSeq implements Sequence {
    private final float[] data;

    public FiniteSeq(float[] data) {
        this.data = data;
    }

    @Override public float get(int idx) {
        return data[idx];
    }

    float[] getData() {
        return data;
    }
    public void put(int idx, float value) {
        data[idx] = value;
    }
    public int getLength() {
        return data.length;
    }

    public static FiniteSeq delta(int size) {
        float[] result = new float[size];
        result[0] = 1;
        return new FiniteSupportSeq(result);
    }
}
