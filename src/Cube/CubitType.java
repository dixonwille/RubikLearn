package Cube;

/**
 * Created by dixon on 5/26/2016.
 */
enum CubitType {
    CORNER(3),
    CENTER(1),
    EDGE(2);

    private int _sides;

    CubitType(int sides){
        this._sides = sides;
    }

    int getNumberOfSides(){
        return this._sides;
    }

    static CubitType find(SideType[] position){
        for(CubitType cubit: CubitType.values()){
            if(cubit.getNumberOfSides() == position.length){
                return cubit;
            }
        }
        return null;
    }
}
