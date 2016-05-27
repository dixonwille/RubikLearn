package Cube;

import javafx.scene.paint.Color;

import java.util.*;

/**
 * Created by dixon on 5/26/2016.
 */
public class Cubit {
    public String Position;
    public CubitType Type;
    public Map<SideType,Color> Colors;

    private static final Map<CubitType, String[]> possiblePositions;
    static {
        possiblePositions = new HashMap<>();
        possiblePositions.put(CubitType.CENTER, new String[]{"t","d","l","r","f","b"});
        possiblePositions.put(CubitType.CORNER, new String[]{"tlf","trf","dlf","drf","tlb","trb","dlb","drb"});
        possiblePositions.put(CubitType.EDGE, new String[]{"tf","tl","tb","tr","df","dl","db","dr","rf","rb","lf","lb"});
    }

    public Cubit(String position) throws Exception{
        this.Position = position;
        CubitType type = CubitType.Find(this.Position);
        if (type == null){
            throw new Exception("Could not find cubit type for " + this.Position);
        }
        this.Type = type;
        if (!hasValidPosition()){
            throw new Exception("Not a valid position of " + this.Type + " with " + this.Position);
        }
    }

    public void SetDefaultColors() throws Exception{
        Map<SideType,Color> colors = new HashMap<>();
        for (char ch: this.Position.toCharArray()) {
            SideType sideType = SideType.Find(ch);
            if(sideType == null){
                throw new Exception("Could not find a side type for " + ch);
            }
            colors.put(sideType, sideType.DefaultColor);
        }
        this.Colors = colors;
    }

    private boolean hasValidPosition(){
        if(this.Position.length() < 1 && this.Position.length() > 3){
            return false;
        }

        List<Character> chs = new ArrayList<>();
        for(char ch: this.Position.toCharArray()){
            if(chs.indexOf(Character.toLowerCase(ch)) > -1){
                return false;
            }else{
                chs.add(Character.toLowerCase(ch));
            }
        }

        return Arrays.asList(possiblePositions.get(this.Type)).indexOf(this.Position) > -1;
    }
}
