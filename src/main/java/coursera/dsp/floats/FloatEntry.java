package coursera.dsp.floats;

public class FloatEntry {
    private final float value;
    private final int index;

    public FloatEntry(float value, int index) {
        this.value = value;
        this.index = index;
    }

    public float getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    @Override public String toString() {
        return value + "@" + index;
    }
}
