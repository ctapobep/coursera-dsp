package coursera.dsp.dft;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    private static float[] real;
    private static float[] imaginary;

    @Override
    public void start(Stage stage) {
        XYChart.Series<Number, Number> reSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> imSeries = new XYChart.Series<>();
        for (int i = 0; i < real.length; i++)
            reSeries.getData().add(new XYChart.Data<>(i, real[i]));
        for (int i = 0; i < imaginary.length; i++)
            imSeries.getData().add(new XYChart.Data<>(i, imaginary[i]));

        XYChart<Number, Number> reChart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
        XYChart<Number, Number> imChart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
        reChart.getData().add(reSeries);
        imChart.getData().add(imSeries);

        FlowPane root = new FlowPane();
        root.getChildren().addAll(reChart, imChart);

        stage.setScene(new Scene(root, 600, 800));
        stage.show();
    }

    public static void main(String[] args) {
        float[][] frequencies = new FourierTransform().analyze(scaled(cos(.125F, 64), 3));
        real = frequencies[0];//cos(.25, 64));
        imaginary = frequencies[1];//cos(.25, 64));
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