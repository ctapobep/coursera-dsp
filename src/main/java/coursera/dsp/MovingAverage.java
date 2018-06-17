package coursera.dsp;

import coursera.dsp.signal.FiniteSeq;
import coursera.dsp.signal.FiniteSupportSeq;
import coursera.dsp.signal.Sequence;

public class MovingAverage {
    public FiniteSeq process(Sequence input, int resultLength, int delay) {
        FiniteSupportSeq output = new FiniteSupportSeq(resultLength);
        for(int i = 0; i < resultLength; i++)
            output.put(i, output.get(i - 1) +
                    (input.get(i) - input.get(i - delay)) / delay);
        return output;
    }
}
