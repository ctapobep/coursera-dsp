package coursera.dsp.wavelets;

import coursera.dsp.signal.FiniteSeq;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import static coursera.dsp.dft.Main.chart;

public class Main extends Application {

    private static FiniteSeq input;
    private static float[] output;

    @Override
    public void start(Stage stage) {
        FlowPane root = new FlowPane(Orientation.HORIZONTAL,
                new FlowPane(chart(input.getArray(), output)));

        stage.setScene(new Scene(root, 1500, 800));
        stage.show();
    }
    public static void main(String[] args) {
        input = FiniteSeq.range(-10F, 10F, .1F);
        output = new HaarWavelet(-1, 3).get(input.getArray());
        launch(args);
    }

}