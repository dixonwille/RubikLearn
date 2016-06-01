/**
 * Created by dixon on 5/26/2016.
 */

import Cube.Canvas.CubeCanvas;
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

            CubeCanvas canvas = new CubeCanvas();
            canvas.SetCubitSize(20);
            canvas.SetCubitPadding(0);
            canvas.SetWidth(1080);
            canvas.SetHeight(720);
            canvas.Render(cube);
            Group root = new Group();
            root.getChildren().add(canvas);

            window.setScene(new Scene(root, 1080, 720));

            window.show();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
