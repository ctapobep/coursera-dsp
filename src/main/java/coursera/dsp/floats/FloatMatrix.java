package coursera.dsp.floats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Was needed in experimental code, not optimal at all. */
public class FloatMatrix {
    private final List<Floats> columns;

    public FloatMatrix(List<Floats> columns) {
        if(columns.size() == 0) throw new IllegalArgumentException("Empty columns");
        this.columns = columns;
    }
    public static FloatMatrix fromRows(List<Floats> rows) {
        return new FloatMatrix(rows).transposed();
    }
    public static FloatMatrix fromRows(float[][] rows) {
        List<Floats> columns = new ArrayList<>();
        for (float[] column : rows)
            columns.add(new Floats(column));
        return new FloatMatrix(columns).transposed();
    }
    public static FloatMatrix fromColumns(Floats col, int n) {
        List<Floats> columns = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            columns.add(col);
        return new FloatMatrix(columns);
    }
    public static FloatMatrix banded(Floats[] bands, int cols, int rows) {
        if(bands.length % 2 != 1) throw new IllegalArgumentException("Don't know which diagonals to choose");
        return banded(bands, (bands.length - 1) / 2, cols, rows);
    }
    public static FloatMatrix banded(Floats[] bands, int bandWidth, int cols, int rows) {
        int [] diagonals = new int[bandWidth * 2 + 1];
        int i = 0;
        for(int band = - bandWidth; band <= bandWidth; band++)
            diagonals[i++] = band;
        return banded(bands, diagonals, cols, rows);
    }
    public static FloatMatrix banded(Floats[] bands, int[] diagonals, int cols, int rows) {
        if(bands.length != diagonals.length)
            throw new IllegalArgumentException("Lengths are different, bands=" + bands.length + ", diags=" + diagonals.length);
        float[][] m = FloatMatrix.fromColumns(Floats.repeat(0, rows), cols).toArray();
        for (int i = 0; i < diagonals.length; i++) {
            int diagonal = diagonals[i];
            Floats band = bands[i];
            for (int c = 0; c < cols; c++) {
                int row = c - diagonal;
                if(row >= rows || row < 0 || c >= band.size())
                    continue;
                m[c][row] = band.get(c);
            }
        }
        return FloatMatrix.fromRows(m);
    }

    public FloatMatrix band(int step) {
        List<Floats> banded = new ArrayList<>(columns.size());
        for (int c = 0; c < columns.size(); c++)
            banded.add(columns.get(c).getBand(c - step, c + step));
        return new FloatMatrix(banded);
    }
    public FloatMatrix band(int ... diagonals) {
        List<Floats> banded = new ArrayList<>(columns.size());
        for (int c = 0; c < columns.size(); c++) {
            int[] indices = new int[diagonals.length];
            for (int i = 0; i < diagonals.length; i++) {
                int idx = diagonals[i];
                indices[i] = c - idx;
            }
            banded.add(columns.get(c).getBand(indices));
        }
        return new FloatMatrix(banded);
    }

    public FloatMatrix transposed() {
        List<Floats> columns = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++)
            columns.add(getRow(i));
        return new FloatMatrix(columns);
    }
    /**
     * Builds an upper triangular matrix, possible with 0 rows (if they were multiples of previous rows).
     */
    public FloatMatrix eliminated() {
        List<Floats> rows = getRows();
        for (int c = 0; c < getWidth(); c++) {
            for(int r = c+1; r < getHeight(); r++) {
                int prevRowIdx = c+1;
                Floats prevRow;
                do {
                    prevRow = rows.get(--prevRowIdx);
                } while(prevRow.isZero());

                Floats row = rows.get(r).minus(prevRow.times(rows.get(r).get(prevRowIdx) / prevRow.get(prevRowIdx)));
                rows.set(r, row);
                if(row.isZero())
                    break;
            }
        }
        return FloatMatrix.fromRows(rows);
    }

    public static FloatMatrix stackVertically(FloatMatrix... matrix) {
        int width = matrix[0].getWidth();
        for (FloatMatrix m : matrix)
            if (m.getWidth() != width) throw new IllegalArgumentException();
        List<Floats> cols = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            Floats newColumn = new Floats();
            for (FloatMatrix m : matrix)
                newColumn = newColumn.append(m.getColumn(c));
            cols.add(newColumn);
        }
        return new FloatMatrix(cols);
    }
    public FloatMatrix times(float factor) {
        List<Floats> columns = new ArrayList<>(getWidth());
        for (Floats next : this.columns)
            columns.add(next.times(factor));
        return new FloatMatrix(columns);
    }
    public FloatMatrix plus(FloatMatrix m) {
        if(m.getWidth() != getWidth() || m.getHeight() != getHeight())
            throw new IllegalArgumentException();
        List<Floats> columns = new ArrayList<>(getWidth());
        for (int i = 0; i < getWidth(); i++)
            columns.add(getColumn(i).plus(m.getColumn(i)));
        return new FloatMatrix(columns);
    }

    public Floats dot(Floats v) {
        if(getWidth() != v.size())
            throw new IllegalArgumentException("Dimensions don't match: " +
                    "this.columns="+getWidth()+ ", but vector size="+ v.size());
        float[] result = new float[getHeight()];
        for(int i = 0; i < getHeight(); i++)
            result[i] = getRow(i).dot(v);
        return new Floats(result);
    }
    public FloatMatrix dot(FloatMatrix m) {
        if (this.getWidth() != m.getHeight() || this.getHeight() != m.getWidth())
            throw new IllegalArgumentException("Dimensions don't match: this=" + this + ", that=" + m);
        List<Floats> result = new ArrayList<>(getWidth());
        for (int col = 0; col < m.getWidth(); col++)
            result.add(dot(m.getColumn(col)));
        return new FloatMatrix(result);
    }
    public int getWidth() {
        return columns.size();
    }
    public int getHeight() {
        return columns.get(0).size();
    }
    public Floats getRow(int row) {
        float[] result = new float[getWidth()];
        for(int i = 0; i < getWidth(); i++)
            result[i] = columns.get(i).get(row);
        return new Floats(result);
    }

    public Floats getColumn(int col) {
        return columns.get(col);
    }
    public float get(int col, int row) {
        return columns.get(col).get(row);
    }
    public List<Floats> getColumns() {
        return columns;
    }
    public List<Floats> getRows() {
        return transposed().getColumns();
    }
    private float[][] toArray() {
        float[][] floats = new float[columns.size()][];
        for (int i = 0; i < columns.size(); i++)
            floats[i] = columns.get(i).toArray();
        return floats;
    }
    public String toStringData() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < getHeight(); i++)
            result.append(getRow(i)).append("\n");
        return result.toString();
    }
    public void assertEquals(FloatMatrix m) {
        if(getHeight() != m.getHeight())
            throw new RuntimeException(String.format("Heights are different: %d and %d", getHeight(), m.getHeight()));
        if(getWidth() != m.getWidth())
            throw new RuntimeException(String.format("Widths are different: %d and %d", getWidth(), m.getWidth()));
        for(int c = 0; c < getWidth(); c++)
            for(int r = 0; r < getHeight(); r++)
                if(get(c, r) != m.get(c, r))
                    throw new RuntimeException(String.format("Values are different at c=%d, r=%d: %f != %f", c, r, get(c, r), m.get(c, r)));
    }
    @Override public String toString() {
        return "Matrix" + getHeight() + 'x' + getWidth();
    }
}
