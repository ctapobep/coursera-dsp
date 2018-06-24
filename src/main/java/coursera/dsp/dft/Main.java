package coursera.dsp.dft;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Arrays;

public class Main extends Application {

    private static float[] data ;

    @Override
    public void start(Stage stage) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < data.length; i++)
            series.getData().add(new XYChart.Data<>(i, data[i]));

        XYChart<Number, Number> chart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
        chart.getData().add(series);
        stage.setScene(new Scene(chart, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        data = new FourierTransform().analyze(cos(.25, 64));//cos(.25, 64));
        launch(args);
    }
    private static float[] cos(double piMultiple, int n) {
        float[] result = new float[n];
        for(int i = 0; i < n; i++)
            result[i] = (float) Math.cos(Math.PI * piMultiple * i);
        return result;
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