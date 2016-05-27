/**
 * Created by dixon on 5/26/2016.
 */

import Cube.CubeCanvas;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Rubik Learn");
        Group root = new Group();
        CubeCanvas canvas = new CubeCanvas(300,250);

        root.getChildren().add(canvas);
        window.setScene(new Scene(root));

        window.show();
    }
}
