package coursera.dsp.signal;

public class PeriodicSignal implements Signal {
    private float[] data;

    PeriodicSignal(FiniteSignal data) { this(data.getData()); }
    PeriodicSignal(float[] data)      { this.data = data; }

    public float get(int idx)     { return data[idx % data.length]; }
    public float[] getOnePeriod() { return data; }

    public static PeriodicSignal sine(int periodLength) {
        float[] result = new float[periodLength];
        for(int i = 0; i < periodLength; i++) {
            result[i] = (float) Math.sin(2 * Math.PI * i/periodLength);
        }
        return new PeriodicSignal(result);
    }
    public static PeriodicSignal sawTooth(int periodLength) {
        float[] result = new float[periodLength];
        for(int i = 0; i < periodLength; i++) {
            result[i] = (float) i/(periodLength/2) - 1;
        }
        return new PeriodicSignal(result);
    }
}
