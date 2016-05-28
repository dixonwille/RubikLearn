package Cube;

import javafx.geometry.Side;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dixon on 5/26/2016.
 */
public enum SideType {
    TOP(ColorType.YELLOW),
    DOWN(ColorType.WHITE),
    LEFT(ColorType.BLUE),
    RIGHT(ColorType.GREEN),
    FRONT(ColorType.RED),
    BACK(ColorType.ORANGE);

    private ColorType _defaultColor;

    SideType(ColorType color){
        this._defaultColor = color;
    }

    public ColorType GetDefaultColor(){
        return this._defaultColor;
    }

    public SideType GetOpposite(){
        switch (this){
            case TOP:
                return DOWN;
            case DOWN:
                return TOP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case FRONT:
                return BACK;
            default:
                return FRONT;
        }
    }

    public List<SideType> GetPossible(){
        List<SideType> possible = new ArrayList<>();
        for(SideType side: SideType.values()){
            if(side != this && side != this.GetOpposite()){
                possible.add(side);
            }
        }
        return possible;
    }
}
