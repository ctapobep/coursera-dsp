package coursera.dsp.floats;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static coursera.dsp.floats.FloatMatrix.fromRows;

public class FloatMatrixTest {
    @Rule public ExpectedException expected = ExpectedException.none();

    @Test public void gaussianEliminationDoesNothingFor1rowMatrix() {
        FloatMatrix m = fromRows(new float[][]{{1, 2}});
        m.assertEquals(m.eliminated());
    }
    @Test public void gaussianEliminationFor2x2LeavesDependentRows0() {
        FloatMatrix m = fromRows(new float[][]{{1, 2}, {2, 4}});
        fromRows(new float[][]{{1, 2}, {0, 0}}).assertEquals(m.eliminated());
    }
    @Test public void gaussianEliminationFor2x2ResultsInUpperTriangular() {
        fromRows(new float[][]{
                {4, 5},
                {2, 5}
        }).eliminated().assertEquals(fromRows(new float[][]{
                {4, 5},
                {0, 2.5F}
        }));
    }
    @Test public void guassianEliminationFor3x2StopsEliminationAt2ndColumn() {
        fromRows(new float[][]{
                {1, 2, 4},
                {2, 1, 5}
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1,  2,  4},
                {0, -3, -3},
        }));
    }
    @Test public void guassianEliminationFor2x3LeavesLastRow0IfItWasDependent() {
        fromRows(new float[][]{
                {1, 2, 4},
                {2, 1, 5},
                {3, 3, 9},
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1,  2,  4},
                {0, -3, -3},
                {0,  0,  0}
        }));
    }
    @Test public void guassianEliminationFor3x3LeavesDependentRowsInMiddle0() {
        fromRows(new float[][]{
                {1, 2, 4},
                {2, 4, 8},
                {3, 3, 9},
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1,  2,  4},
                {0,  0,  0},
                {0, -3, -3}
        }));
    }
    @Test public void guassianEliminationCanLeave0rowsBothInMiddleAndAtEnd() {
        fromRows(new float[][]{
                {1, 2, 4},
                {2, 4, 8},
                {3, 3, 9},
                {3, 3, 9}
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1,  2,  4},
                {0,  0,  0},
                {0, -3, -3},
                {0,  0,  0}
        }));
    }
    @Test public void guassianEliminationForCanLeaveMultiple0rowsInMiddle() {
        fromRows(new float[][]{
                {1, 2, 4},
                {2, 4, 8},
                {2, 4, 8},
                {3, 3, 9},
                {3, 3, 9}
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1,  2,  4},
                {0,  0,  0},
                {0,  0,  0},
                {0, -3, -3},
                {0,  0,  0}
        }));
    }
    @Test public void gaussianEliminationFor3x3ResultsInUpperTriangular() {
        fromRows(new float[][]{
                {1, 2, 4},
                {2, 1, 5},
                {2, 2, 1}
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1,  2,  4},
                {0, -3, -3},
                {0,  0, -5}
        }));
    }

    @Test public void guassianEliminationFor3x2WithLastColumnDependent() {
        System.out.println(fromRows(new float[][]{
                {15, 40, 100},
                {25, -50, 50}
        }).eliminated().toStringData());
        fromRows(new float[][]{
                {15, 40, 100},
                {25, -50, 50}
        }).eliminated().assertEquals(fromRows(new float[][]{
                {1, 0, 4},
                {0, 1, 1}
        }));
    }

    @Test public void matricesAreEqualIfAllElementsAreEqual() {
        fromRows(new float[][]{{15, 40, 100}, {25, -50, 50}})
                .assertEquals(fromRows(new float[][]{{15, 40, 100}, {25, -50, 50}}));
    }
    @Test public void matricesAreNotEqual_ifHeightsAreDifferent() {
        expected.expectMessage("Heights are different: 1 and 2");
        fromRows(new float[][]{{1}}).assertEquals(fromRows(new float[][]{{2}, {3}}));
    }
    @Test public void matricesAreNotEqual_ifWidthsAreDifferent() {
        expected.expectMessage("Widths are different: 2 and 3");
        fromRows(new float[][]{{1, 2}}).assertEquals(fromRows(new float[][]{{2, 3, 1}}));
    }
    @Test public void matricesAreNotEqual_ifElementsHaveDifferentValues() {
        expected.expectMessage("Values are different at c=0, r=1: 1.000000 != 2.000000");
        fromRows(new float[][]{{1, 2}, {1, 3}}).assertEquals(fromRows(new float[][]{{1, 2}, {2, 3}}));
    }

}