package Cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dixon on 5/26/2016.
 */
enum SideType {
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

    ColorType GetDefaultColor(){
        return this._defaultColor;
    }

    SideType GetOpposite(){
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

    List<SideType> GetPossible(){
        List<SideType> possible = new ArrayList<>();
        for(SideType side: SideType.values()){
            if(side != this && side != this.GetOpposite()){
                possible.add(side);
            }
        }
        return possible;
    }

    //Less Than Map for base side.
    //This is to be able to orient the cubits on each side correctly
    //Defines the top left as the smallest number (first and second numbers)
    //Defines the bottom right as the largest number (third and fourth number)
    //The last two sides are a tie breaker which will be less than the other indeterminant.
    //Tie breaker is used when the opposite corners cannot be compared.
    //USED ONLY WITH CORNERS AND THE EDGES CAN BE INFERRED FROM THERE
    List<SideType> lTMap(){
        switch (this){
            case TOP:
                return Arrays.asList(SideType.LEFT, SideType.BACK, SideType.FRONT, SideType.RIGHT, SideType.BACK, SideType.RIGHT);
            case DOWN:
                return Arrays.asList(SideType.LEFT, SideType.FRONT, SideType.BACK, SideType.RIGHT, SideType.FRONT, SideType.RIGHT);
            case LEFT:
                return Arrays.asList(SideType.TOP, SideType.BACK, SideType.FRONT, SideType.DOWN, SideType.TOP, SideType.FRONT);
            case RIGHT:
                return Arrays.asList(SideType.TOP, SideType.FRONT, SideType.BACK, SideType.DOWN, SideType.TOP, SideType.BACK);
            case FRONT:
                return Arrays.asList(SideType.TOP, SideType.LEFT, SideType.RIGHT, SideType.DOWN, SideType.TOP, SideType.RIGHT);
            default:
                return Arrays.asList(SideType.TOP, SideType.RIGHT, SideType.LEFT, SideType.DOWN, SideType.TOP, SideType.LEFT);
        }
    }

    static String toString(SideType[] sides){
        String s = "";
        for(SideType side: sides){
            s += side.toString().toCharArray()[0];
        }
        return s;
    }
}
