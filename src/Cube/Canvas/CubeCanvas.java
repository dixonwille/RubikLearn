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

    public CubeCanvas(){
        super(Default._width, Default._height);
        setDefaults();
    }

    public CubeCanvas(double width, double height){
        super(width, height);
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

    public void SetLineWidth(double lw){
        _gc.setLineWidth(lw);
        Side.SetLineWidth(_gc.getLineWidth());
    }

    public void Render(Cube.Cube cube){
        _sides.clear();
        List<Cube.Side> sides = cube.GetSides();
        sides.forEach(side -> _sides.add(new Side(side, _gc)));
        _sides.forEach(Side::Render);
    }

    private void setDefaults(){
        _gc = this.getGraphicsContext2D();
        _gc.setStroke(Default._strokeColor);
        this.SetLineWidth(Default._lineWidth);
        _sides = new ArrayList<>();
        Side.SetSize(Default._cubitSize);
        Side.SetPadding(Default._cubitPadding);
    }
}
