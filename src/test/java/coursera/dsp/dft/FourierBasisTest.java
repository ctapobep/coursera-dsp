package coursera.dsp.dft;

import org.junit.Test;

import static org.junit.Assert.*;

public class FourierBasisTest {
    @Test public void test() {
        FourierBasis f = new FourierBasis(64);
        assertEquals(0F, f.get(0, 0).getPiMultiple(), .0000001);
        assertEquals(1F, f.get(1, 1).getPiMultiple(), .0000001);
        assertEquals(2F, f.get(2, 1).getPiMultiple(), .0000001);
    }
}