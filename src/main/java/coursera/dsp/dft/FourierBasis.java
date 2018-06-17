package coursera.dsp.dft;

public class FourierBasis {
    private final EulerEquation[][] rows;

    public FourierBasis(int n) {
        this.rows = new EulerEquation[n][n];
        float nF = n;
        for(int row = 0; row < n; row++)
            for(int col = 0; col < n; col++)
                rows[row][col] = new EulerEquation((col * row)/nF);
    }

    public EulerEquation get(int row, int col) {
        return rows[row][col];
    }

    public float[] innerProduct(float[] signal) {
        float[] frequencies = new float[signal.length];
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            EulerEquation[] row = rows[rowIdx];
            float sum = 0;
            for (int col = 0; col < row.length; col++) {
                EulerEquation cell = row[col];
                sum += cell.getRe() * signal[col];
            }
            frequencies[rowIdx] = sum;
        }
        return frequencies;
    }
}
