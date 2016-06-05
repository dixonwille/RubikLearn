package Cube;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dixon on 5/27/2016.
 */
public class Cube {
    private List<Cubit> _cubits;
    private List<Side> _sides;
    private List<ChangeListener> _moveListeners;

    public Cube(){
        try {
            _moveListeners = new ArrayList<>();
            createCubits();
            separateSides();
        }catch (CubeException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public List<Side> getSides(){
        return _sides;
    }

    public void reset(){
        _cubits.forEach(Cubit::reset);
        changed();
    }

    public void addMoveListener(ChangeListener listener){
        _moveListeners.add(listener);
    }

    public void move(MoveType move){
        _cubits.forEach(cubit -> cubit.move(move));
        changed();
    }

    //Cubits are setup here so that I have the minimum required cubits
    private void createCubits() throws CubeException{
        _cubits = new ArrayList<>();
        List<SideType> sidesFinished = new ArrayList<>();
        for(SideType centerSide: SideType.values()){
            _cubits.add(new Cubit(new SideType[]{centerSide})); //Create the center cubit

            List<SideType> possibleEdges = centerSide.getPossible();
            possibleEdges.removeAll(sidesFinished); //Remove all sides that are already finished.

            List<SideType> edgesFinished = new ArrayList<>();
            for(SideType edgeSide: possibleEdges){
                _cubits.add(new Cubit(new SideType[]{centerSide,edgeSide})); //Create the edge for centerSide and edgeSide

                List<SideType> possibleCorners = edgeSide.getPossible();
                possibleCorners.removeAll(sidesFinished); //Remove all sides that are already finished.
                possibleCorners.removeAll(edgesFinished); //Remove the edges that we completed.
                possibleCorners.remove(centerSide); //Remove the main side we are currently on.
                possibleCorners.remove(centerSide.getOpposite()); //Remove the impossible side as well.

                for(SideType cornerSide: possibleCorners){
                    _cubits.add(new Cubit(new SideType[]{centerSide,edgeSide,cornerSide})); //Create the corner for centerSide, edgeSide and cornerSide
                }
                edgesFinished.add(edgeSide);
            }
            sidesFinished.add(centerSide);
        }
    }

    private void separateSides(){
        _sides = new ArrayList<>();
        for(SideType side: SideType.values()){
            _sides.add(new Side(side,Side.getCubits(side,_cubits)));
        }
    }

    private void changed(){
        separateSides();
        //Tell Listeners we moved
        for(ChangeListener listener: _moveListeners){
            listener.Changed();
        }
    }
}
