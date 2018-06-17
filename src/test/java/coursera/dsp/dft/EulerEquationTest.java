package coursera.dsp.dft;

import org.junit.Test;

import static org.junit.Assert.*;

public class EulerEquationTest {
    @Test public void realPart() {
        assertEquals(1, new EulerEquation(0).getRe(), 0.0001);
        assertEquals(0, new EulerEquation(.5F).getRe(), 0.0001);
        assertEquals(-1, new EulerEquation(1).getRe(), 0.0001);
        assertEquals(0, new EulerEquation(1.5F).getRe(), 0.0001);
        assertEquals(1, new EulerEquation(2).getRe(), 0.0001);
    }
    @Test public void imaginaryPart() {
        assertEquals(0, new EulerEquation(0).getIm(), 0.0001);
        assertEquals(1, new EulerEquation(.5F).getIm(), 0.0001);
        assertEquals(0, new EulerEquation(1).getIm(), 0.0001);
        assertEquals(-1, new EulerEquation(1.5F).getIm(), 0.0001);
        assertEquals(0, new EulerEquation(2).getIm(), 0.0001);
    }
}