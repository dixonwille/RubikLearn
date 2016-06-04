package Cube;

import java.util.*;
import java.util.List;

/**
 * Created by dixon on 5/26/2016.
 */
class Cubit{
    private CubitType _type;
    private Map<SideType,ColorType> _colors;


    Cubit(SideType[] position) throws CubeException{
        _colors = new HashMap<>();
        //Initialize the type of cubit
        CubitType type = CubitType.find(position);
        if (type == null){
            throw new CubeException("Could not find cubit type for " + SideType.toString(position));
        }
        this._type = type;

        //Set the default color (change later if need be)
        for(SideType side: position){
            this._colors.put(side,side.getDefaultColor());
        }

        //Check that we have a valid position set
        if (!isValid()){
            throw new CubeException("Not a valid position of " + this._type + " with " + SideType.toString(position));
        }
    }

    boolean isOnSide(SideType side){
        for(SideType cubitSide: this._colors.keySet()){
            if (cubitSide == side){
                return true;
            }
        }
        return false;
    }

    void reset(){
        List<ColorType> colors = new ArrayList<>(_colors.values());
        this._colors.clear();
        colors.forEach(color -> this._colors.put(SideType.getByColor(color), color));
    }

    ColorType getColor(SideType side){
        return _colors.get(side);
    }

    List<SideType> getSides(){
        return new ArrayList<>(this._colors.keySet());
    }

    List<SideType> otherSides(Cubit cubit){
        //This is so we do not modify the original cubits sides.
        List<SideType> otherSides = new ArrayList<>(this._colors.keySet());
        otherSides.removeAll(cubit._colors.keySet());
        return otherSides;
    }
    
    boolean cornerLessThan(SideType base, Cubit query) throws CubeException{
        //Only compare corners
        if(this._type != CubitType.CORNER || query._type != CubitType.CORNER) {
            throw new CubeException("Can only compare corners");
        }

        List<SideType> ltMap = base.lTMap();
        List<SideType> comparisonMap = ltMap.subList(0,4);
        List<SideType> tieBreaker = ltMap.subList(4,6);
        //The way the cube is made only sides left are opposite of each other.
        List<SideType> thisDiff = this.otherSides(query);
        List<SideType> queryDiff = query.otherSides(this);

        //Only compare cubits on the same sides
        if(thisDiff.size() > CubitType.CORNER.getNumberOfSides() - 1 || queryDiff.size() > CubitType.CORNER.getNumberOfSides() - 1){
            throw new CubeException("Corners must have a side in common");
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
            SideType querySide = queryDiff.get(queryDiff.indexOf(thisSide.getOpposite()));
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

    void move(MoveType move){
        if (this.isEffected(move)) {
            List<SideType> direction = move.getClockWise();
            if(move.toString().length() == 3){
                Collections.reverse(direction);
            }


            Map<SideType,ColorType> tmpColors = new HashMap<>();
            for(int i = 0; i < direction.size(); i++){
                SideType oldSide = direction.get(i);
                SideType newSide = direction.get(i == direction.size() - 1 ? 0 : i + 1);

                if(this._colors.containsKey(oldSide)) {
                    tmpColors.put(newSide, this._colors.remove(oldSide));
                }
            }

            for(Map.Entry<SideType,ColorType> entry: tmpColors.entrySet()){
                this._colors.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private boolean isEffected(MoveType move){
        List<SideType> effectedSides = move.getEffectedSides();

        if(effectedSides.size() == 1){
            return this._type != CubitType.CENTER && this._colors.containsKey(effectedSides.get(0));
        }else{
            if(this._type == CubitType.CORNER){
                return false;
            }

            //if we are working with an edge all sides must be the same
            //if we are working with a center only one side must match
            int matchedSides = 0;
            for(SideType side: effectedSides) {
                if (this._colors.containsKey(side)) {
                    matchedSides += 1;
                }

                if (matchedSides == this._type.getNumberOfSides()) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean isValid(){
        if(this._colors.entrySet().size() != this._type.getNumberOfSides()){
            return false;
        }

        List<ColorType> colors = new ArrayList<>();
        List<SideType> sides = new ArrayList<>();
        for(Map.Entry<SideType,ColorType> sideColor: this._colors.entrySet()){
            //Check if duplicated Side or Color exists
            if(sides.indexOf(sideColor.getKey()) > -1 || colors.indexOf(sideColor.getValue()) > -1){
                return false;
                //Check if the opposite Side or Color exists
            }else if(sides.indexOf(sideColor.getKey().getOpposite()) > -1 || colors.indexOf(sideColor.getKey().getOpposite().getDefaultColor()) > -1) {
                return false;
            }else{
                sides.add(sideColor.getKey());
                colors.add(sideColor.getValue());
            }
        }
        return true;
    }

    static List<Cubit> getCubitsOfType(CubitType type, List<Cubit> cubits){
        List<Cubit> cubitsOfT = new ArrayList<>();
        cubits.forEach(cubit -> {
            if (cubit._type == type){
                cubitsOfT.add(cubit);
            }
        });
        return cubitsOfT;
    }
}
