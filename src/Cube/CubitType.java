package Cube;

import javafx.geometry.Side;

/**
 * Created by dixon on 5/26/2016.
 */
public enum CubitType {
    CORNER(3),
    CENTER(1),
    EDGE(2);

    private int _sides;

    CubitType(int sides){
        this._sides = sides;
    }

    public int GetNumberOfSides(){
        return this._sides;
    }

    public static CubitType Find(SideType[] position){
        for(CubitType cubit: CubitType.values()){
            if(cubit.GetNumberOfSides() == position.length){
                return cubit;
            }
        }
        return null;
    }
}
