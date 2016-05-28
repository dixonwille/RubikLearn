package Cube;

import javafx.scene.paint.Color;

/**
 * Created by dixon on 5/27/2016.
 */
public enum ColorType {
    YELLOW(Color.YELLOW),
    WHITE(Color.WHITE),
    RED(Color.RED),
    ORANGE(Color.ORANGE),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE);

    private Color _color;

    ColorType(Color color){
        this._color = color;
    }

    public Color GetColor(){
        return _color;
    }

    public ColorType GetOpposite(){
        switch (this){
            case YELLOW:
                return WHITE;
            case WHITE:
                return YELLOW;
            case RED:
                return ORANGE;
            case ORANGE:
                return RED;
            case GREEN:
                return BLUE;
            default:
                return GREEN;
        }
    }
}
