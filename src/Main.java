/**
 * Created by dixon on 5/26/2016.
 */

import Cube.Cube;
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
        try {
            Cube cube = new Cube();
            window = primaryStage;
            window.setTitle("Rubik Learn");

            //CubeCanvas canvas = new CubeCanvas();
            Group root = new Group();
            //root.getChildren().add(canvas);

            window.setScene(new Scene(root, 300, 250));

            window.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
