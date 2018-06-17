package coursera.dsp.signal;

import java.util.Random;

public class FiniteSupportSeq extends FiniteSeq {

    public FiniteSupportSeq(PeriodicSeq periodic) {
        this(periodic.getOnePeriod());
    }
    public FiniteSupportSeq(int size) {
        super(new float[size]);
    }
    public FiniteSupportSeq(float[] data) {
        super(data);
    }

    @Override public float get(int idx) {
        if(idx < 0 || idx > getLength() - 1) return 0;
        return super.get(idx);
    }

    public static FiniteSupportSeq random(int length) {
        Random r = new Random();
        float[] result = new float[length];
        for(int i = 0; i < length; i++) {
            int sign = r.nextBoolean() ? 1 : -1;
            result[i] = sign * r.nextFloat();
        }
        return new FiniteSupportSeq(result);
    }
}
