package coursera.dsp;

import coursera.dsp.signal.FiniteSeq;
import coursera.dsp.signal.FiniteSupportSeq;
import coursera.dsp.signal.Sequence;

public class SignalProcessor {
    public FiniteSeq process(Sequence input, int resultLength, float attenuation, int delay) {
        FiniteSupportSeq result = new FiniteSupportSeq(new float[resultLength]);
        for (int i = 0; i < resultLength; i++)
            result.put(i, input.get(i) + attenuation * result.get(i - delay));
        return result;
    }
}
