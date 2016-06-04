package Cube.Canvas;

import Cube.ColorType;
import Cube.SideType;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dixon on 5/31/2016.
 */
class Side {
    private Cube.ColorType[][] _colorMatrix;
    private GraphicsContext _gc;
    private static final Map<SideType, List<Integer>> _sidePosition;
    private SideType _side;
    static private final int _cubitsOnSide = 3;
    static private double _cubitSize, _cubitPadding, _lineWidth, _canvasPadding;

    static {
        _sidePosition = new HashMap<>();
        _sidePosition.put(SideType.UP, Arrays.asList(1, 0));
        _sidePosition.put(SideType.DOWN, Arrays.asList(1, 2));
        _sidePosition.put(SideType.FRONT, Arrays.asList(1, 1));
        _sidePosition.put(SideType.BACK, Arrays.asList(3, 1));
        _sidePosition.put(SideType.LEFT, Arrays.asList(0, 1));
        _sidePosition.put(SideType.RIGHT, Arrays.asList(2, 1));
    }

    Side(Cube.Side side, GraphicsContext gc) {
        _gc = gc;
        _side = side.getSideType();
        _colorMatrix = side.getColorMatrix();
    }

    void render() {
        double[] cords = getStartingPosition(_side);
        //includes padding and line width
        double totalSize = _cubitSize + _cubitPadding + (_lineWidth/2);
        for(int r = 0; r < _colorMatrix.length; r++){
            for(int c = 0; c < _colorMatrix.length; c++){
                Color paintColor = getPaintColor(_colorMatrix[c][r]);
                _gc.setFill(paintColor);
                _gc.fillRect((cords[0] + (r * totalSize)), (cords[1] + (c * totalSize)), _cubitSize, _cubitSize);
                _gc.strokeRect((cords[0] + (r * totalSize)), (cords[1] + (c * totalSize)), _cubitSize, _cubitSize);
                if(r == 1 && c == 1){
                    _gc.setTextAlign(TextAlignment.CENTER);
                    _gc.setTextBaseline(VPos.CENTER);
                    _gc.strokeText(String.valueOf(_side.toString().charAt(0)), (cords[0] + (r * totalSize) + (totalSize/2)), (cords[1] + (c * totalSize) + (totalSize/2)));
                }
            }
        }
    }

    //Static because all sides will have the same size
    static double size() {
        //includes padding only on one of the sides of the side
        return (_cubitsOnSide * (_cubitSize + _cubitPadding + (_lineWidth/2)));
    }

    static void setSize(double size) {
        _cubitSize = size;
    }

    static void setPadding(double padding) {
        _cubitPadding = padding;
    }

    static void setLineWidth(double lw){
        _lineWidth = lw;
    }

    static void setCanvasPadding(double padding) {
        _canvasPadding = padding;
    }

    private static Color getPaintColor(ColorType colorType) {
        switch (colorType) {
            case YELLOW:
                return Color.YELLOW;
            case WHITE:
                return Color.WHITE;
            case RED:
                return Color.RED;
            case ORANGE:
                return Color.ORANGE;
            case GREEN:
                return Color.GREEN;
            default:
                return Color.BLUE;
        }
    }

    private static double[] getStartingPosition(SideType sideType) {
        double sideSize = size();
        double padding =  _cubitPadding + (_lineWidth/2) + _canvasPadding;
        switch (sideType) {
            case UP:
                return new double[]{sideSize * _sidePosition.get(SideType.UP).get(0) + padding, sideSize * _sidePosition.get(SideType.UP).get(1) + padding};
            case DOWN:
                return new double[]{sideSize * _sidePosition.get(SideType.DOWN).get(0) + padding, sideSize * _sidePosition.get(SideType.DOWN).get(1) + padding};
            case LEFT:
                return new double[]{sideSize * _sidePosition.get(SideType.LEFT).get(0) + padding, sideSize * _sidePosition.get(SideType.LEFT).get(1) + padding};
            case RIGHT:
                return new double[]{sideSize * _sidePosition.get(SideType.RIGHT).get(0) + padding, sideSize * _sidePosition.get(SideType.RIGHT).get(1) + padding};
            case FRONT:
                return new double[]{sideSize * _sidePosition.get(SideType.FRONT).get(0) + padding, sideSize * _sidePosition.get(SideType.FRONT).get(1) + padding};
            default:
                return new double[]{sideSize * _sidePosition.get(SideType.BACK).get(0) + padding, sideSize * _sidePosition.get(SideType.BACK).get(1) + padding};
        }
    }
}
