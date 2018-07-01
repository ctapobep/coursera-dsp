package coursera.dsp.dft;

public class ComplexNumber {
    public static ComplexNumber ZERO= new ComplexNumber(0, 0);
    private final float im, re;

    public ComplexNumber(float re, float im) {
        this.im = im;
        this.re = re;
    }
    public static ComplexNumber complex(float re, float im){
        return new ComplexNumber(re, im);
    }

    public ComplexNumber times(ComplexNumber another) {
        float re = another.re * this.re - another.im * this.im;
        float im = another.im * this.re + another.re * this.im;
        return new ComplexNumber(re, im);
    }
    public ComplexNumber plus(ComplexNumber another) {
        return new ComplexNumber(this.re + another.re, this.im + another.im);
    }
    public ComplexNumber divide(float divisor){
        return scale(1/divisor);
    }
    public ComplexNumber scale(float scaler) {
        return new ComplexNumber(re * scaler, im * scaler);
    }
    public float getIm() {
        return im;
    }
    public float getRe(){
        return re;
    }
    public float getLength() {
        return (float) Math.sqrt(im * im + re*re);
    }

    public static float[] toIms(ComplexNumber[] complexNumbers) {
        float[] im = new float[complexNumbers.length];
        for (int i = 0; i < complexNumbers.length; i++)
            im[i] = complexNumbers[i].getIm();
        return im;
    }
    public static float[] toRes(ComplexNumber[] complexNumbers) {
        float[] re = new float[complexNumbers.length];
        for (int i = 0; i < complexNumbers.length; i++)
            re[i] = complexNumbers[i].getRe();
        return re;
    }
    public static float[] toLengths(ComplexNumber[] complexNumbers) {
        float[] re = new float[complexNumbers.length];
        for (int i = 0; i < complexNumbers.length; i++)
            re[i] = complexNumbers[i].getLength();
        return re;
    }
}
