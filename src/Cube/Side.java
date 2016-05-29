package Cube;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dixon on 5/27/2016.
 */
class Side{
    private List<Cubit> _cubits;
    private SideType _side;
    private Cubit[][] _orderedCubits;

    Side(SideType side, List<Cubit> cubits){
        this._side = side;
        this._cubits = cubits;
        orderCubits();
    }

    private void orderCubits(){
        _orderedCubits = new Cubit[3][3];
        Cubit center = Cubit.GetCubitsOfType(CubitType.CENTER, _cubits).get(0); //Only one center for each side
        List<Cubit> edges = Cubit.GetCubitsOfType(CubitType.EDGE, _cubits);
        List<Cubit> corners = Cubit.GetCubitsOfType(CubitType.CORNER, _cubits);
        _orderedCubits[1][1] = center;

        //Compare corners to each other first the infer the edges from those corners.
        corners = sortCorners(corners);
        int[] cords = new int[]{0,0};
        int upperBound = _orderedCubits.length - 1;
        for(Cubit corner: corners){
            _orderedCubits[cords[0]][cords[1]] = corner;
            cords[1] += upperBound;
            if(cords[1] > upperBound){
                cords[0] += upperBound;
                cords[1] = 0;
            }
        }

        //Infer edges from corners
        for(int r = 0; r < _orderedCubits.length; r++){
            for(int c = 0; c < _orderedCubits.length; c++){
                if(_orderedCubits[c][r] == null){
                    //Find the corners to infer from
                    List<Cubit> queryCorners;
                    if(c == upperBound || c == 0){
                        //edges on the left and right
                        queryCorners = Arrays.asList(_orderedCubits[c][r+1], _orderedCubits[c][r-1]);
                    }else{
                        //edges on the top and bottom
                        queryCorners = Arrays.asList(_orderedCubits[c+1][r], _orderedCubits[c-1][r]);
                    }

                    //Only need to use one corner to find edge after I find what side is not identical to the other corner.
                    List<SideType> diffSides = queryCorners.get(0).OtherSides(queryCorners.get(1));
                    List<SideType> cornerSides = queryCorners.get(0).GetSides();
                    cornerSides.removeAll(diffSides);
                    cornerSides.remove(_side); //Should only contain one corner now.

                    //find the proper edge to place in this position
                    for(Cubit edge:edges){
                        if(edge.IsOnSide(cornerSides.get(0))){
                            _orderedCubits[c][r] = edge;
                        }
                    }
                }
            }
        }
    }

    private List<Cubit> sortCorners(List<Cubit> corners){
        Collections.sort(corners, (Cubit o1, Cubit o2) -> {
            try {
                int o1Ans = o1.cornerLessThan(_side, o2) ? 1 : 0;
                int o2Ans = o2.cornerLessThan(_side, o1) ? 1 : 0;
                return o2Ans - o1Ans;
            }catch (Exception e){
                System.out.println(e);
                return 0;
            }
        });
        return corners;
    }

    static List<Cubit> GetCubits(SideType side,List<Cubit> cubits){
        List<Cubit> sideCubit = new ArrayList<>();
        cubits.forEach(cubit -> {
            if(cubit.IsOnSide(side)){
                sideCubit.add(cubit);
            }
        });
        return sideCubit;
    }
}
