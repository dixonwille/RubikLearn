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
            canvas.SetWidth(canvas.GetWidth());
            canvas.SetHeight(canvas.GetHeight());
            canvas.Render(cube);

            Button clearBtn = new Button("Clear");
            clearBtn.setOnAction(e -> canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight()));

            Button redrawBtn = new Button("Redraw");
            redrawBtn.setOnAction(e -> canvas.Render(cube));

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
