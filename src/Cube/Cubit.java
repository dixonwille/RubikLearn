package Cube;

import javafx.scene.paint.Color;

import java.util.*;

/**
 * Created by dixon on 5/26/2016.
 */
public class Cubit{
    public CubitType Type;
    public Map<SideType,ColorType> Colors;

    public Cubit(SideType[] position) throws Exception{
        Colors = new HashMap<>();
        //Initialize the type of cubit
        CubitType type = CubitType.Find(position);
        if (type == null){
            throw new Exception("Could not find cubit type for " + position.toString());
        }
        this.Type = type;

        //Set the default color (change later if need be)
        for(SideType side: position){
            this.Colors.put(side,side.GetDefaultColor());
        }

        //Check that we have a valid position set
        if (!isValid()){
            throw new Exception("Not a valid position of " + this.Type + " with " + position.toString());
        }
    }

    private boolean isValid(){
        if(this.Colors.entrySet().size() != this.Type.GetNumberOfSides()){
            return false;
        }

        List<ColorType> colors = new ArrayList<>();
        List<SideType> sides = new ArrayList<>();
        for(Map.Entry<SideType,ColorType> sideColor: this.Colors.entrySet()){
            //Check if duplicated Side or Color exists
            if(sides.indexOf(sideColor.getKey()) > -1 || colors.indexOf(sideColor.getValue()) > -1){
                return false;
                //Check if the opposite Side or Color exists
            }else if(sides.indexOf(sideColor.getKey().GetOpposite()) > -1 || colors.indexOf(sideColor.getValue().GetOpposite()) > -1) {
                return false;
            }else{
                sides.add(sideColor.getKey());
                colors.add(sideColor.getValue());
            }
        }
        return true;
    }
}
