package coursera.dsp.dft;

public class FourierBasis {
    private final EulerEquation[][] rows;

    public FourierBasis(int n) {
        this.rows = new EulerEquation[n][n];
        float nF = n;
        for(int row = 0; row < n; row++)
            for(int col = 0; col < n; col++)
                rows[row][col] = new EulerEquation((col * row) / nF);
    }

    public EulerEquation get(int row, int col) {
        return rows[row][col];
    }

    public float[][] innerProduct(float[] signal) {
        float[][] frequencies = new float[2][signal.length];
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            EulerEquation[] row = rows[rowIdx];
            float realSum = 0, imaginarySum = 0;
            for (int col = 0; col < row.length; col++) {
                realSum += row[col].getRe() * signal[col];
                imaginarySum += row[col].getIm() * signal[col];
            }
            frequencies[0][rowIdx] = realSum;
            frequencies[1][rowIdx] = imaginarySum;
        }
        return frequencies;
    }
    public float[] linearlyCombine(float[] coefficients) {
        float[] result = new float[coefficients.length];
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            EulerEquation[] row = rows[rowIdx];
            float sum = 0;
            for (int col = 0; col < row.length; col++)
                sum += row[col].getRe() * coefficients[col];
            result[rowIdx] = sum / coefficients.length;
        }
        return result;
    }
}
