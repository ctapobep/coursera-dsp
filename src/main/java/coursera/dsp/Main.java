package coursera.dsp;

import coursera.dsp.signal.FiniteSeq;
import coursera.dsp.signal.FiniteSupportSeq;
import coursera.dsp.signal.PeriodicSeq;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

public class Main extends Application {

    private static FiniteSeq data = new FiniteSeq(new float[0]);

    @Override
    public void start(Stage stage) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < data.size(); i++)
            series.getData().add(new XYChart.Data<>(i, data.get(i)));

        XYChart<Number, Number> chart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
        chart.getData().add(series);
        stage.setScene(new Scene(chart, 800, 600));
        stage.show();
    }

    public static void main(String[] args) {
        data = new SignalProcessor().process(FiniteSeq.delta(100), 100, .7F, 3);
        data = new SignalProcessor().process(new FiniteSupportSeq(PeriodicSeq.sine(100)), 1000, .9F, 100);
        data = new SignalProcessor().process(FiniteSupportSeq.random(100), 1000, .95F, 100);
        data = new MovingAverage().process(PeriodicSeq.sine(100), 1000, 10);
        launch(args);
    }


}