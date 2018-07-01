package coursera.dsp.dft;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FourierTransformTest {
//    @Test public void deltaFunctionResultsInFreqIntensity1() {
//        float[] delta = new float[64];
//        delta[0] = 1F;
//        float[] frequencies = new FourierTransform().analyze(delta);
//        for (float frequency : frequencies)
//            assertEquals(1., frequency, .0001);
//
//        float[] synthesizedSignal = new FourierTransform().synthesize(frequencies);
//        for (int i = 0; i < delta.length; i++)
//            assertEquals(delta[i], synthesizedSignal[i], .00001);
//    }
//    @Test public void unitFunctionResultsInFreqIntensity1OnlyAtTheBeginning() {
//        float[] unit = new float[64];
//        Arrays.fill(unit, 1F);
//        float[] frequencies = new FourierTransform().analyze(unit);
//        assertEquals(64., frequencies[0], .0001);
//        for (int i = 1; i < frequencies.length; i++)
//            assertTrue(frequencies[i] < 1.001);
//        float[] synthesizedSignal = new FourierTransform().synthesize(frequencies);
//        for (int i = 0; i < unit.length; i++)
//            assertEquals(unit[i], synthesizedSignal[i], .00001);
//    }
//    @Test public void analyze() {
//        float[] frequencies = new FourierTransform().analyze(cos(.25, 64));
//        float max = Integer.MIN_VALUE;
//        int index = -1;
//        for (int i = 0; i < frequencies.length; i++) {
//            float frequency = frequencies[i];
//            if(frequency > max) {
//                max = frequency;
//                index = i;
//            }
//        }
//        assertEquals(16, index);
//    }

    private static float[] cos(double piMultiple, int n) {
        float[] result = new float[n];
        for(int i = 0; i < n; i++)
            result[i] = (float) Math.cos(Math.PI * piMultiple * i);
        return result;
    }
}