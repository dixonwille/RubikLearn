package Cube;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


/**
 * Created by dixon on 5/26/2016.
 */
public class CubeCanvas extends Canvas {
    private GraphicsContext _gc;
    private float _width, _space, _lw;
    public CubeCanvas(double width, double height){
        super(width, height);
        _gc = this.getGraphicsContext2D();
        _width = 10;
        _space = 3;
        _lw = 1;

        drawCube();
    }
    private void drawCube(){

    }
}
