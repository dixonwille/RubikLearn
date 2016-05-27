package Cube;

import javafx.scene.paint.Color;

/**
 * Created by dixon on 5/26/2016.
 */
public enum SideType {
    TOP(Color.YELLOW, 't'),
    BOTTOM(Color.WHITE, 'd'),
    LEFT(Color.BLUE, 'l'),
    RIGHT(Color.GREEN, 'r'),
    FRONT(Color.RED, 'f'),
    BACK(Color.ORANGE, 'b');

    public Color DefaultColor;
    private char _posIndicator;
    SideType(Color color, char pos){
        this.DefaultColor = color;
        this._posIndicator = pos;
    }
    public char GetIndicator(){
        return this._posIndicator;
    }

    public static SideType Find(char ch){
        for(SideType side: SideType.values()){
            if (Character.toLowerCase(side.GetIndicator()) == Character.toLowerCase(ch)){
                return side;
            }
        }
        return null;
    }
}
