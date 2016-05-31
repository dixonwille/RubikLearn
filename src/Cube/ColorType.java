package Cube;

import javafx.scene.paint.Color;

/**
 * Created by dixon on 5/27/2016.
 */
enum ColorType {
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

    //TODO Abstract this away out of this package. It belongs outside this package.
    Color GetFXColor(){
        return this._color;
    }

    //TODO Remove this and use the sideTypes default color to infer opposite
    ColorType GetOpposite(){
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
