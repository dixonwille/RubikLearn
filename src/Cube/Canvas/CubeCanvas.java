package Cube.Canvas;

import Cube.ChangeListener;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dixon on 5/31/2016.
 */
public class CubeCanvas extends Canvas implements ChangeListener{
    private GraphicsContext _gc;
    private List<Side> _sides;
    private double _canvasPadding;
    private Cube.Cube _cube;

    public CubeCanvas(Cube.Cube cube){
        super();
        _cube = cube;
        cube.addMoveListener(this);
        setDefaults();
    }

    public void setCubitSize(double size){
        Side.setSize(size);
    }

    public void setCubitPadding(double padding){
        Side.setPadding(padding);
    }

    public void setCanvasPadding(double padding) {
        _canvasPadding = padding;
        Side.setCanvasPadding(_canvasPadding);
    }

    public void setLineWidth(double lw){
        _gc.setLineWidth(lw);
        Side.setLineWidth(_gc.getLineWidth());
    }

    public double getCubeWidth(){
        return Side.size() * 4 + (_gc.getLineWidth()/2);
    }

    public double getCubeHeight(){
        return Side.size() * 3 + (_gc.getLineWidth()/2);
    }

    public double getCanvasWidth(){
        return getCubeWidth() + (_canvasPadding * 2);
    }

    public double getCanvasHeight(){
        return getCubeHeight() + (_canvasPadding * 2);
    }

    public void render(){
        this.clear();
        _sides.clear();
        List<Cube.Side> sides = _cube.getSides();
        sides.forEach(side -> _sides.add(new Side(side, _gc)));
        _sides.forEach(Side::render);
    }

    public void clear(){
        _gc.clearRect(0,0,this.getWidth(),this.getHeight());
        _sides.clear();
    }

    private void setDefaults(){
        _gc = this.getGraphicsContext2D();
        _gc.setStroke(Default._strokeColor);
        this.setLineWidth(Default._lineWidth);
        this.setCanvasPadding(Default._canvasPadding);
        _sides = new ArrayList<>();
        this.setCubitSize(Default._cubitSize);
        this.setCubitPadding(Default._cubitPadding);
    }

    @Override
    public void Changed() {
        render();
    }
}
