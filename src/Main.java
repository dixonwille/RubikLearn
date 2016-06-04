/**
 * Created by dixon on 5/26/2016.
 */

import Cube.Canvas.CubeCanvas;
import Cube.MoveType;
import Cube.Cube;
import javafx.application.Application;
import javafx.geometry.Pos;
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
            //Then update the canvases width and height using the getCanvasWidth and Height Methods
            canvas.setWidth(canvas.getCanvasWidth());
            canvas.setHeight(canvas.getCanvasHeight());
            //render the cube
            canvas.render(cube);

            BorderPane root = new BorderPane();
            HBox hb = new HBox();

            for(MoveType mt: MoveType.values()) {
                Button btn = new Button(mt.toString());
                btn.setOnAction(e -> {
                    cube.move(mt);
                    canvas.render(cube);
                });
                hb.getChildren().add(btn);
            }

            Button resetBtn = new Button("Reset");
            resetBtn.setOnAction(e -> {
                cube.reset();
                canvas.render(cube);
            });
            hb.getChildren().add(resetBtn);

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
