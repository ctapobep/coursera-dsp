package coursera.dsp.dft;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    private static float[] input;
    private static float[] real;
    private static float[] imaginary;

    @Override
    public void start(Stage stage) {
        Series<Number, Number> reSeries = new Series<>(),
                imSeries = new Series<>(),
                inputSeries = new Series<>();
        for (int i = 0; i < input.length; i++) {
            reSeries.getData().add(new Data<>(i, real[i]));
            imSeries.getData().add(new Data<>(i, imaginary[i]));
            inputSeries.getData().add(new Data<>(i, input[i]));
        }

        XYChart<Number, Number> reChart = new ScatterChart<>(new NumberAxis(), new NumberAxis()),
                imChart = new ScatterChart<>(new NumberAxis(), new NumberAxis()),
                inputChart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
        reChart.getData().add(reSeries);
        imChart.getData().add(imSeries);
        inputChart.getData().add(inputSeries);

        FlowPane root = new FlowPane(Orientation.HORIZONTAL,
                inputChart,
                new FlowPane(reChart, imChart));

        stage.setScene(new Scene(root, 1000, 800));
        stage.show();
    }

    public static void main(String[] args) {
        input = scaled(cos(.125F, 64), 3);
        float[][] frequencies = new FourierTransform().analyze(input);
        real = frequencies[0];
        imaginary = frequencies[1];
        launch(args);
    }
    private static float[] cos(float piMultiple, int n) {
        float[] result = new float[n];
        for(int i = 0; i < n; i++)
            result[i] = (float) Math.cos(Math.PI * piMultiple * i);
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

}