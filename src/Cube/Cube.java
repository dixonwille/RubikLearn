package Cube;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dixon on 5/27/2016.
 */
public class Cube {
    private List<Cubit> _cubits;
    private List<Side> _sides;

    public Cube(){
        try {
            createCubits();
            separateSides();
        }catch (CubeException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Side> GetSides(){
        return _sides;
    }

    //Cubits are setup here so that I have the minimum required cubits
    private void createCubits() throws CubeException{
        _cubits = new ArrayList<>();
        List<SideType> sidesFinished = new ArrayList<>();
        for(SideType centerSide: SideType.values()){
            _cubits.add(new Cubit(new SideType[]{centerSide})); //Create the center cubit

            List<SideType> possibleEdges = centerSide.GetPossible();
            possibleEdges.removeAll(sidesFinished); //Remove all sides that are already finished.

            List<SideType> edgesFinished = new ArrayList<>();
            for(SideType edgeSide: possibleEdges){
                _cubits.add(new Cubit(new SideType[]{centerSide,edgeSide})); //Create the edge for centerSide and edgeSide

                List<SideType> possibleCorners = edgeSide.GetPossible();
                possibleCorners.removeAll(sidesFinished); //Remove all sides that are already finished.
                possibleCorners.removeAll(edgesFinished); //Remove the edges that we completed.
                possibleCorners.remove(centerSide); //Remove the main side we are currently on.
                possibleCorners.remove(centerSide.GetOpposite()); //Remove the impossible side as well.

                for(SideType cornerSide: possibleCorners){
                    _cubits.add(new Cubit(new SideType[]{centerSide,edgeSide,cornerSide})); //Create the corner for centerSide, edgeSide and cornerSide
                }
                edgesFinished.add(edgeSide);
            }
            sidesFinished.add(centerSide);
        }
    }

    public void Reset(){
        _cubits.forEach(Cubit::Reset);
    }

    private void separateSides(){
        _sides = new ArrayList<>();
        for(SideType side: SideType.values()){
            _sides.add(new Side(side,Side.GetCubits(side,_cubits)));
        }
    }


}
