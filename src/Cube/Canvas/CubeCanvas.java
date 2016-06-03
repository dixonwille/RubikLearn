package Cube.Canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dixon on 5/31/2016.
 */
public class CubeCanvas extends Canvas{
    private GraphicsContext _gc;
    private List<Side> _sides;
    private double _canvasPadding;

    public CubeCanvas(){
        super();
        setDefaults();
    }

    public void SetHeight(double height){
        super.setHeight(height);
    }

    public void SetWidth(double width){
        super.setWidth(width);
    }

    public void SetCubitSize(double size){
        Side.SetSize(size);
    }

    public void SetCubitPadding(double padding){
        Side.SetPadding(padding);
    }

    public void SetCanvasPadding(double padding) {
        _canvasPadding = padding;
        Side.SetCanvasPadding(_canvasPadding);
    }

    public void SetLineWidth(double lw){
        _gc.setLineWidth(lw);
        Side.SetLineWidth(_gc.getLineWidth());
    }

    public double GetCubeWidth(){
        return Side.Size() * 4 + (_gc.getLineWidth()/2);
    }

    public double GetCubeHeight(){
        return Side.Size() * 3 + (_gc.getLineWidth()/2);
    }

    public double GetCanvasWidth(){
        return GetCubeWidth() + (_canvasPadding * 2);
    }

    public double GetCanvasHeight(){
        return GetCubeHeight() + (_canvasPadding * 2);
    }

    public void Render(Cube.Cube cube){
        this.Clear();
        List<Cube.Side> sides = cube.GetSides();
        sides.forEach(side -> _sides.add(new Side(side, _gc)));
        _sides.forEach(Side::Render);
    }

    public void Clear(){
        _gc.clearRect(0,0,this.getWidth(),this.getHeight());
        _sides.clear();
    }

    private void setDefaults(){
        _gc = this.getGraphicsContext2D();
        _gc.setStroke(Default._strokeColor);
        this.SetLineWidth(Default._lineWidth);
        this.SetCanvasPadding(Default._canvasPadding);
        _sides = new ArrayList<>();
        this.SetCubitSize(Default._cubitSize);
        this.SetCubitPadding(Default._cubitPadding);
    }
}
