package coursera.dsp.dft;

import org.junit.Test;

import static coursera.dsp.dft.EulerEquation.ZERO;
import static coursera.dsp.dft.EulerEquation.eip;
import static io.qala.datagen.RandomShortApi.Double;
import static io.qala.datagen.RandomShortApi.integer;
import static org.junit.Assert.assertEquals;

public class EulerEquationTest {
    @Test public void realPart() {
        assertEquals(1, new EulerEquation(0).getRe(), 0.0001);
        assertEquals(0, new EulerEquation(.5F).getRe(), 0.0001);
        assertEquals(-1, new EulerEquation(1).getRe(), 0.0001);
        assertEquals(0, new EulerEquation(1.5F).getRe(), 0.0001);
        assertEquals(1, new EulerEquation(2).getRe(), 0.0001);

        float random = (float) Double(-100, 100);
        assertEquals(new EulerEquation(random).getRe(), new EulerEquation(-1 * random).getRe(), 0.0001);
    }
    @Test public void imaginaryPart() {
        assertEquals(0, new EulerEquation(0).getIm(), 0.0001);
        assertEquals(1, new EulerEquation(.5F).getIm(), 0.0001);
        assertEquals(0, new EulerEquation(1).getIm(), 0.0001);
        assertEquals(-1, new EulerEquation(1.5F).getIm(), 0.0001);
        assertEquals(0, new EulerEquation(2).getIm(), 0.0001);

        float random = (float) Double(-100, 100);
        assertEquals(new EulerEquation(random).getIm(), new EulerEquation(-1 * random).getIm() * -1, 0.0001);
    }
    @Test public void multiplicationByZeroIsNoOp() {
        EulerEquation e = new EulerEquation((float) Double(-100, 100));
        assertEquals(e.getIm(), e.multiply(ZERO).getIm(), 0.0001);
        assertEquals(e.getRe(), e.multiply(ZERO).getRe(), 0.0001);
    }
    @Test public void multiplicationBy2πMultipleIsNoOp() {
        EulerEquation multiple2π = new EulerEquation(2 * integer(-500, 500)/*high floating point error afterwards*/);
        EulerEquation e = new EulerEquation((float) Double(-100, 100));
        assertEquals(e.getIm(), e.multiply(multiple2π).getIm(), 0.0001);
        assertEquals(e.getRe(), e.multiply(multiple2π).getRe(), 0.0001);
    }
    @Test public void multiplicationExamples() {
        assertEipEquals(eip(1), eip(.5F).multiply(eip(.5F)));

        float f = (float) Double(-100, 100);
        assertEipEquals(ZERO, eip(f).multiply(eip(f).conjugate()));
        assertEipEquals(ZERO, eip(f).conjugate().multiply(eip(f)));
    }

    public void assertEipEquals(EulerEquation e1, EulerEquation e2) {
        assertEquals("Real", e1.getRe(), e2.getRe(), .00001);
        assertEquals("Imaginary", e1.getIm(), e2.getIm(), .00001);
    }
}