package coursera.dsp.dft;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    private static float[] input;
    private static ComplexNumber[] output;
    private static ComplexNumber[] frequencies;

    static float[] basisRe, basisIm;

    @Override
    public void start(Stage stage) {
        float[] modulus = new float[input.length];
        float[] argument = new float[input.length];
        for (int i = 0; i < input.length; i++) {
            modulus[i] = (float) Math.sqrt(Math.pow(frequencies[i].getIm(), 2) + Math.pow(frequencies[i].getRe(), 2));
            if (Math.abs(frequencies[i].getRe()) > 0.01)
                argument[i] = (float) Math.atan(frequencies[i].getIm()/frequencies[i].getRe());
        }
        FlowPane root = new FlowPane(Orientation.HORIZONTAL,
                new FlowPane(chart(input), chart(ComplexNumber.toRes(output))),
                new FlowPane(chart(ComplexNumber.toRes(frequencies)), chart(ComplexNumber.toIms(frequencies))),
                new FlowPane(chart(modulus), chart(argument)));

        stage.setScene(new Scene(root, 1500, 800));
        stage.show();
    }
    public static void main(String[] args) {
        int length = 64;
//        input = new float[64];
//        Arrays.fill(input, 0, 1, 1);
        input = scaled(cos(Math.PI/3., .2F, length), 3);
        frequencies = new FourierTransform().analyze(input);

        basisRe = new float[length];
        basisIm = new float[length];

        EulerEquation[] row = new FourierBasis(length).rows[4];
        for (int i = 0; i < row.length; i++) {
            basisRe[i] = row[i].getRe();
            basisIm[i] = row[i].getIm();
        }

        output = new FourierTransform().synthesize(frequencies);
        launch(args);
    }

    private static float[] cos(float piMultiple, int n) {
        return cos(0, piMultiple, n);
    }
    private static float[] cos(double phase, float piMultiple, int n) {
        float[] result = new float[n];
        for(int i = 0; i < n; i++)
            result[i] = (float) Math.cos(phase + Math.PI * piMultiple * i);
        return result;
    }
    private static float[] sin(float piMultiple, int n) {
        return sin(0, piMultiple, n);
    }
    private static float[] sin(double phase, float piMultiple, int n) {
        float[] result = new float[n];
        for(int i = 0; i < n; i++)
            result[i] = (float) Math.sin(phase + Math.PI * piMultiple * i);
        return result;
    }
    private static float[] scaled(float[] vector, float multiple) {
        for (int i = 0; i < vector.length; i++)
            vector[i] = vector[i] * multiple;
        return vector;
    }
    private static float[] unitStep(int n) {
        float[] result = new float[n];
        Arrays.fill(result, 1F);
        return result;
    }
    private static float[] delta(int n) {
        float[] result = new float[n];
        result[0] = 1F;
        return result;
    }

    public static XYChart<Number, Number> chart(float[] data) {
        Series<Number, Number> reSeries = new Series<>();
        for (int i = 0; i < data.length; i++)
            reSeries.getData().add(new Data<>(i, data[i]));
        XYChart<Number, Number> reChart = new AreaChart<>(new NumberAxis(), new NumberAxis());
        reChart.getData().add(reSeries);
        return reChart;
    }
    public static XYChart<Number, Number> chart(float[] domain, float[] data) {
        Series<Number, Number> reSeries = new Series<>();
        for (int i = 0; i < data.length; i++)
            reSeries.getData().add(new Data<>(domain[i], data[i]));
        XYChart<Number, Number> reChart = new AreaChart<>(new NumberAxis(), new NumberAxis());
        reChart.getData().add(reSeries);
        return reChart;
    }
}