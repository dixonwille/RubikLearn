package Cube;

/**
 * Created by dixon on 5/26/2016.
 */
public enum CubitType {
    CORNER(3),
    CENTER(1),
    EDGE(2);

    public int Sides;

    CubitType(int sides){
        this.Sides = sides;
    }

    public static CubitType Find(String Position){
        for(CubitType cubit: CubitType.values()){
            if(cubit.Sides == Position.length()){
                return cubit;
            }
        }
        return null;
    }
}
