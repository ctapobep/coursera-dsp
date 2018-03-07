package coursera.dsp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Main extends Application {

    private static float[] data = new float[]{1, 2, 3, 4};

    @Override
    public void start(Stage stage) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < data.length; i++)
            series.getData().add(new XYChart.Data<>(i, data[i]));

        ScatterChart<Number, Number> chart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
        chart.getData().add(series);
        stage.setScene(new Scene(chart, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}