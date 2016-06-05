/**
 * Created by dixon on 5/26/2016.
 */

import Cube.Canvas.CubeCanvas;
import Cube.MoveType;
import Cube.Cube;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.security.Key;

public class Main extends Application {

    private Cube _cube;
    private CubeCanvas _canvas;
    private MoveType _mt = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            _cube = new Cube();
            _canvas = new CubeCanvas();

            //Must first allow defaults to be set.
            //Then change any padding and sizes as need be.
            //Then update the canvases width and height using the getCanvasWidth and Height Methods
            _canvas.setWidth(_canvas.getCanvasWidth());
            _canvas.setHeight(_canvas.getCanvasHeight());
            //render the cube
            _canvas.render(_cube);

            //Place for all layouts to be handled
            BorderPane root = new BorderPane();

            GridPane buttonLayout = createButtons();

            root.setCenter(_canvas);
            root.setRight(buttonLayout);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("styles/rubikLearnStyle.css");
            //Listen for key presses to do things accordingly
            scene.setOnKeyPressed(this::keyEvent);
            scene.setOnKeyReleased(this::keyEvent);

            primaryStage.setTitle("Rubik Learn");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void keyEvent(KeyEvent event){
        if(event.getEventType().getName() == "KEY_PRESSED") {
            switch (event.getText().toLowerCase()) {
                case "f":
                    _mt = event.isShiftDown() ? MoveType.FCC : MoveType.FC;
                    break;
                case "b":
                    _mt = event.isShiftDown() ? MoveType.BCC : MoveType.BC;
                    break;
                case "l":
                    _mt = event.isShiftDown() ? MoveType.LCC : MoveType.LC;
                    break;
                case "r":
                    _mt = event.isShiftDown() ? MoveType.RCC : MoveType.RC;
                    break;
                case "u":
                    _mt = event.isShiftDown() ? MoveType.UCC : MoveType.UC;
                    break;
                case "d":
                    _mt = event.isShiftDown() ? MoveType.DCC : MoveType.DC;
                    break;
                case "m":
                    _mt = event.isShiftDown() ? MoveType.MCC : MoveType.MC;
                    break;
                case "s":
                    _mt = event.isShiftDown() ? MoveType.SCC : MoveType.SC;
                    break;
                case "e":
                    _mt = event.isShiftDown() ? MoveType.ECC : MoveType.EC;
                    break;
                default:
                    _mt = null;
                    break;
            }
        }

        if(event.getEventType().getName() == "KEY_RELEASED" && _mt != null){
            _cube.move(_mt);
            _canvas.render(_cube);
            _mt = null;
        }
    }

    private GridPane createButtons(){
        //The Layout fot the buttons (kind of like a number pad on a phone)
        GridPane buttonLayout = new GridPane();
        buttonLayout.setVgap(20);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(0,10,0,0)); //Same spacing as the canvas on other side.
        //8 columns with the 3rd and 6th being spacers
        for(int i =0; i < 8; i++){
            ColumnConstraints column = new ColumnConstraints();
            //Depending on the column in the group of 3 depends on the style.
            switch (i%3){
                case 0:
                    column.setHalignment(HPos.RIGHT);
                    break;
                case 1:
                    column.setHalignment(HPos.LEFT);
                    break;
                default: //2
                    column.setPrefWidth(5);
            }
            buttonLayout.getColumnConstraints().add(column);
        }

        int[] position = new int[]{0,0};
        //Make all the movement buttons
        for(MoveType mt: MoveType.values()) {
            //Create the button
            //The way the enum is ordered allows me to know that button pairs are created sequentially
            Button btn = new Button(mt.toString());
            btn.setOnAction(e -> {
                _cube.move(mt);
                _canvas.render(_cube);
            });
            //Add Styles to button depending on where they are
            if(position[0]%3 == 0){
                btn.getStyleClass().add("rightAlign");
            }else{
                btn.getStyleClass().add("leftAlign");
            }

            //Add button to grid.
            buttonLayout.add(btn,position[0],position[1]);

            //increment position
            position[0]++;

            //If we are in a spacing column skip and go to next
            if(position[0]%3 == 2){
                position[0]++;
            }

            //If we reached our max size move to the next row
            if(position[0] >= 8){
                position[0] = 0;
                position[1]++;
            }
        }

        Button resetBtn = new Button("Reset");
        resetBtn.setOnAction(e -> {
            _cube.reset();
            _canvas.render(_cube);
        });

        //Have the reset button positioned in the center of the center two columns
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(resetBtn);
        buttonLayout.add(hb,3,position[1],2,1);

        return buttonLayout;
    }
}
