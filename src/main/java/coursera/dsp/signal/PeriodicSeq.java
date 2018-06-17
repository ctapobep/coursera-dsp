package coursera.dsp.signal;

public class PeriodicSeq implements Sequence {
    private float[] data;

    PeriodicSeq(FiniteSeq data) { this(data.getData()); }
    PeriodicSeq(float[] data)      { this.data = data; }

    public float get(int idx)     {
        return data[Math.floorMod(idx, data.length)];
    }
    public float[] getOnePeriod() { return data; }

    public static PeriodicSeq sine(int periodLength) {
        float[] result = new float[periodLength];
        for(int i = 0; i < periodLength; i++) {
            result[i] = (float) Math.sin(2 * Math.PI * i/periodLength);
        }
        return new PeriodicSeq(result);
    }
    public static PeriodicSeq sawTooth(int periodLength) {
        float[] result = new float[periodLength];
        for(int i = 0; i < periodLength; i++) {
            result[i] = (float) i/(periodLength/2) - 1;
        }
        return new PeriodicSeq(result);
    }
}
