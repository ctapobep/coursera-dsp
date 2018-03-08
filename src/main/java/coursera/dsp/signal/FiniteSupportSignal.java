package coursera.dsp.signal;

public class FiniteSupportSignal implements Signal {
    private final float[] data;

    public FiniteSupportSignal(PeriodicSignal periodic) {
        this(periodic.getOnePeriod());
    }
    public FiniteSupportSignal(float[] data) {
        this.data = data;
    }

    @Override public float get(int idx) {
        if(idx < 0 || idx > data.length - 1) return 0;
        return data[idx];
    }
}
