package coursera.dsp;

import coursera.dsp.signal.Signal;

public class SignalProcessor {
    public float[] process(Signal input, int resultLength, float attenuation, int delay) {
        float[] result = new float[resultLength];
        for (int i = 0; i < resultLength; i++) {
            float currentDelay = delay > i ? 0 : result[i - delay];
            result[i] = input.get(i) + attenuation * currentDelay;
        }
        return result;
    }
}
