package Cube;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by dixon on 5/26/2016.
 */
class Cubit implements Comparable<Cubit>{
    private CubitType _type;
    private Map<SideType,ColorType> _colors;

    //TODO create my own error types
    Cubit(SideType[] position) throws Exception{
        _colors = new HashMap<>();
        //Initialize the type of cubit
        CubitType type = CubitType.Find(position);
        if (type == null){
            throw new Exception("Could not find cubit type for " + position);
        }
        this._type = type;

        //Set the default color (change later if need be)
        for(SideType side: position){
            this._colors.put(side,side.GetDefaultColor());
        }

        //Check that we have a valid position set
        if (!isValid()){
            throw new Exception("Not a valid position of " + this._type + " with " + position);
        }
    }

    boolean IsOnSide(SideType side){
        for(SideType cubitSide: this._colors.keySet()){
            if (cubitSide == side){
                return true;
            }
        }
        return false;
    }

    private List<SideType> otherSides(Cubit cubit){
        //This is so we do not modify the original cubits sides.
        List<SideType> otherSides = new ArrayList<>(this._colors.keySet());
        otherSides.removeAll(cubit._colors.keySet());
        return otherSides;
    }

    private boolean isValid(){
        if(this._colors.entrySet().size() != this._type.GetNumberOfSides()){
            return false;
        }

        List<ColorType> colors = new ArrayList<>();
        List<SideType> sides = new ArrayList<>();
        for(Map.Entry<SideType,ColorType> sideColor: this._colors.entrySet()){
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
    //TODO Create my own error types
    boolean cornerLessThan(SideType base, Cubit query) throws Exception{
        //Only compare corners
        if(this._type != CubitType.CORNER || query._type != CubitType.CORNER) {
            throw new Exception("Can only compare corners");
        }

        List<SideType> ltMap = base.lTMap();
        List<SideType> comparisonMap = ltMap.subList(0,4);
        List<SideType> tieBreaker = ltMap.subList(4,6);
        //The way the cube is made only sides left are opposite of each other.
        List<SideType> thisDiff = this.otherSides(query);
        List<SideType> queryDiff = query.otherSides(this);

        //Only compare cubits on the same sides
        if(thisDiff.size() > CubitType.CORNER.GetNumberOfSides() - 1 || queryDiff.size() > CubitType.CORNER.GetNumberOfSides() - 1){
            throw new Exception("Corners must have a side in common");
        }

        //Simple comparison if they are neighbors
        if(thisDiff.size() == 1 && queryDiff.size() == 1){
            return comparisonMap.indexOf(thisDiff.get(0)) < comparisonMap.indexOf(queryDiff.get(0));
        }

        //Check for the case that the corners are comparable
        //If both sides are less than or greater than each other.
        boolean previousAnswer = false; //random but will get initialized on first loop
        for(int i = 0; i < thisDiff.size(); i++){
            //Should only compare opposite sides
            SideType thisSide = thisDiff.get(i);
            SideType querySide = queryDiff.get(queryDiff.indexOf(thisSide.GetOpposite()));
            if(i == 0){
                previousAnswer = comparisonMap.indexOf(thisSide) < comparisonMap.indexOf(querySide);
            }else if(previousAnswer == comparisonMap.indexOf(thisSide) < comparisonMap.indexOf(querySide)){
                return previousAnswer;
            }
        }

        //Use tie breaker
        if(thisDiff.contains(tieBreaker.get(0)) && thisDiff.contains(tieBreaker.get(1))){
            return true;
        }else if(queryDiff.contains(tieBreaker.get(0)) && queryDiff.contains(tieBreaker.get(1))){
            return false;
        }

        //How did you get here? I went through all possibilities
        return false;
    }

    static List<Cubit> GetCubitsOfType(CubitType type, List<Cubit> cubits){
        List<Cubit> cubitsOfT = new ArrayList<>();
        cubits.forEach(cubit -> {
            if (cubit._type == type){
                cubitsOfT.add(cubit);
            }
        });
        return cubitsOfT;
    }

    @Override
    public int compareTo(Cubit other) {
        return 0;
    }
}
