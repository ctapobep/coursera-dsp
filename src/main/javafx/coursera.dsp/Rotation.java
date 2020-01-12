package coursera.dsp;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Rotation extends Application {

    @Override public void start(Stage stage) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>((float) 0, (float) 0));

        NumberAxis xAxis = new NumberAxis(); xAxis.setAutoRanging(false);xAxis.setUpperBound(2); xAxis.setLowerBound(-2);
        NumberAxis yAxis = new NumberAxis(); yAxis.setAutoRanging(false);yAxis.setUpperBound(2); yAxis.setLowerBound(-2);
        XYChart<Number, Number> chart = new ScatterChart<>(xAxis, yAxis);
        chart.getData().add(series);
        stage.setScene(new Scene(chart, 800, 600));
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                actionEvent -> {
                    double seconds = (double) System.currentTimeMillis() / 1000 + 1;
                    XYChart.Data<Number, Number> point = series.getData().get(0);
                    float x = (float) point.getXValue(), y = (float) point.getYValue();

                    double angle = -1 * ((Math.PI / 30) * seconds - Math.PI/2);
                    point.setXValue((float) Math.cos(angle));
                    point.setYValue((float) Math.sin(angle));
                }
        ));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
