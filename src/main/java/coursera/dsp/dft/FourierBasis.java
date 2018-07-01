package coursera.dsp.dft;

public class FourierBasis {
    public final EulerEquation[][] rows;

    public FourierBasis(int n) {
        this.rows = new EulerEquation[n][n];
        //noinspection UnnecessaryLocalVariable
        float nF = n;
        for(int row = 0; row < n; row++)
            for(int col = 0; col < n; col++)
                rows[row][col] = new EulerEquation(2 * (col * row) / nF);
    }

    public EulerEquation get(int row, int col) {
        return rows[row][col];
    }

    public ComplexNumber[] innerProduct(float[] signal) {
        ComplexNumber[] result = new ComplexNumber[signal.length];
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            EulerEquation[] row = rows[rowIdx];
            float reSum = 0, imSum = 0;
            for (int col = 0; col < row.length; col++) {
                EulerEquation conjugate = row[col].conjugate();
                reSum += conjugate.getRe() * signal[col];
                imSum += conjugate.getIm() * signal[col];
            }
            result[rowIdx] = new ComplexNumber(reSum, imSum);
        }
        return result;
    }
    public ComplexNumber[] linearlyCombine(ComplexNumber[] frequencies) {
        ComplexNumber[] result = new ComplexNumber[frequencies.length];
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            EulerEquation[] row = rows[rowIdx];
            ComplexNumber sum = ComplexNumber.ZERO;
            for (int col = 0; col < row.length; col++)
                sum = sum.plus(row[col].toComplex().times(frequencies[col]));
            result[rowIdx] = sum.divide(frequencies.length);
        }
        return result;
    }
}
