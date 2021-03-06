package Cube;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dixon on 5/27/2016.
 */
public enum MoveType {
    FC(SideType.FRONT),
    FCC(SideType.FRONT),
    BC(SideType.BACK),
    BCC(SideType.BACK),
    LC(SideType.LEFT),
    LCC(SideType.LEFT),
    RC(SideType.RIGHT),
    RCC(SideType.RIGHT),
    UC(SideType.UP),
    UCC(SideType.UP),
    DC(SideType.DOWN),
    DCC(SideType.DOWN),
    MC(SideType.FRONT, SideType.UP, SideType.BACK, SideType.DOWN),
    MCC(SideType.FRONT, SideType.UP, SideType.BACK, SideType.DOWN),
    EC(SideType.FRONT, SideType.RIGHT, SideType.BACK, SideType.LEFT),
    ECC(SideType.FRONT, SideType.RIGHT, SideType.BACK, SideType.LEFT),
    SC(SideType.UP, SideType.RIGHT, SideType.DOWN, SideType.LEFT),
    SCC(SideType.UP, SideType.RIGHT, SideType.DOWN, SideType.LEFT);

    private List<SideType> _effectedSides;

    MoveType(SideType... sides){
        _effectedSides = Arrays.asList(sides);
    }

    List<SideType> getEffectedSides(){
        return _effectedSides;
    }

    List<SideType> getClockWise(){
        switch (this){
            case FC:
            case FCC:
            case SC:
            case SCC:
                return Arrays.asList(SideType.UP,SideType.RIGHT,SideType.DOWN,SideType.LEFT);
            case BC:
            case BCC:
                return Arrays.asList(SideType.UP,SideType.LEFT,SideType.DOWN,SideType.RIGHT);
            case LC:
            case LCC:
            case MC:
            case MCC:
                return Arrays.asList(SideType.UP,SideType.FRONT,SideType.DOWN,SideType.BACK);
            case RC:
            case RCC:
                return Arrays.asList(SideType.UP,SideType.BACK,SideType.DOWN,SideType.FRONT);
            case UC:
            case UCC:
                return Arrays.asList(SideType.FRONT,SideType.LEFT,SideType.BACK,SideType.RIGHT);
            default:
                return Arrays.asList(SideType.FRONT,SideType.RIGHT,SideType.BACK,SideType.LEFT);
        }
    }
}
