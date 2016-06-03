/**
 * Created by dixon on 5/26/2016.
 */

import Cube.Canvas.CubeCanvas;
import Cube.Cube;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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

            CubeCanvas canvas = new CubeCanvas();
            //Must first allow defaults to be set.
            //Then change any padding and sizes as need be.
            //Then update the canvases width and height using the GetCanvasWidth and Height Methods
            canvas.SetWidth(canvas.GetCanvasWidth());
            canvas.SetHeight(canvas.GetCanvasHeight());
            //Render the cube
            canvas.Render(cube);

            Button clearBtn = new Button("Clear");
            clearBtn.setOnAction(e -> canvas.Clear());

            Button redrawBtn = new Button("Reset");
            redrawBtn.setOnAction(e -> {
                cube.Reset();
                canvas.Render(cube);
            });

            BorderPane root = new BorderPane();
            HBox hb = new HBox();
            hb.getChildren().addAll(clearBtn, redrawBtn);
            hb.setAlignment(Pos.CENTER_LEFT);
            root.setCenter(canvas);
            root.setBottom(hb);
            Scene scene = new Scene(root);

            window = primaryStage;
            window.setTitle("Rubik Learn");
            window.setScene(scene);
            window.show();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
