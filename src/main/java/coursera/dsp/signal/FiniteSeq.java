package coursera.dsp.signal;

import java.util.ArrayList;
import java.util.List;

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
    public int size() {
        return data.length;
    }

    public float[] getArray() {
        return data;
    }

    public static FiniteSeq delta(int size) {
        float[] result = new float[size];
        result[0] = 1;
        return new FiniteSupportSeq(result);
    }
    public static FiniteSeq range(float from, float to, float step) {
        List<Float> result = new ArrayList<>();
        for(float f = from; f < to; f+=step)
            result.add(f);
        float[] resultArray = new float[result.size()];
        for(int i = 0; i < result.size(); i++)
            resultArray[i] = result.get(i);
        return new FiniteSeq(resultArray);
    }
}
