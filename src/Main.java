/**
 * Created by dixon on 5/26/2016.
 */

import Cube.Cube;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
            Label label = new Label(Integer.toString(cube.NumberOfCubits()));
            StackPane layout = new StackPane();
            layout.getChildren().add(label);

            window.setScene(new Scene(layout, 300, 250));

            window.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
