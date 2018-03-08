package coursera.dsp;

import coursera.dsp.signal.FiniteSignal;
import coursera.dsp.signal.FiniteSupportSignal;
import coursera.dsp.signal.PeriodicSignal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class Main extends Application {

    private static float[] data = new float[]{1, 2, 3, 4};

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
        data = new SignalProcessor().process(FiniteSignal.delta(100), 100, .7F, 3);
        data = new SignalProcessor().process(new FiniteSupportSignal(PeriodicSignal.sine(100)), 1000, .9F, 100);
        data = new SignalProcessor().process(new FiniteSupportSignal(PeriodicSignal.sawTooth(100)), 1000, .95F, 100);
        launch(args);
    }


}