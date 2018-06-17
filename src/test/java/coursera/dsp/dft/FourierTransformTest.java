package coursera.dsp.dft;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FourierTransformTest {
    @Test public void analyze() {
        float[] frequencies = new FourierTransform().analyze(cos(.25, 64));
        float max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < frequencies.length; i++) {
            float frequency = frequencies[i];
            if(frequency > max) {
                max = frequency;
                index = i;
            }
        }
        assertEquals(8, index);
    }

    private static float[] cos(double piMultiple, int n) {
        float[] result = new float[n];
        for(int i = 0; i < n; i++)
            result[i] = (float) Math.cos(Math.PI * piMultiple * i);
        return result;
    }
}