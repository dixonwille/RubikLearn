package Cube;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        int upperBound = _orderedCubits[0].length - 1;
        for(Cubit corner: corners){
            _orderedCubits[cords[0]][cords[1]] = corner;
            cords[1] = cords[1] + upperBound;
            if(cords[1] > upperBound){
                cords[0] = cords[0] + upperBound;
                cords[1] = 0;
            }
        }

        //Infer edges from corners
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
