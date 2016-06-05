import Cube.Cube;
import Cube.MoveType;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by willdixon on 6/5/16.
 */
class CubeEventHandler implements EventHandler<KeyEvent> {
    private Cube _cube;
    private List<KeyCode> _keysPressed;

    CubeEventHandler(Cube cube){
        _cube = cube;
        _keysPressed = new ArrayList<>();
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType().getName().equals("KEY_PRESSED")) {
            keyPressed(event);
        }else if(event.getEventType().getName().equals("KEY_RELEASED")){
            keyReleased(event);
        }
    }

    private void keyPressed(KeyEvent event){
        if(!_keysPressed.contains(event.getCode())) {
            _keysPressed.add(event.getCode());
        }
    }

    private void keyReleased(KeyEvent event){
        _keysPressed.remove(event.getCode());
        if(_keysPressed.size() <= 1){
            MoveType mt = null;
            boolean resetCube = false;
            switch (event.getCode().getName().toLowerCase()) {
                case "f":
                    mt = event.isShiftDown() ? MoveType.FCC : MoveType.FC;
                    break;
                case "b":
                    mt = event.isShiftDown() ? MoveType.BCC : MoveType.BC;
                    break;
                case "r":
                    mt = event.isShiftDown() ? MoveType.RCC : MoveType.RC;
                    break;
                case "l":
                    mt = event.isShiftDown() ? MoveType.LCC : MoveType.LC;
                    break;
                case "u":
                    mt = event.isShiftDown() ? MoveType.UCC : MoveType.UC;
                    break;
                case "d":
                    mt = event.isShiftDown() ? MoveType.DCC : MoveType.DC;
                    break;
                case "m":
                    mt = event.isShiftDown() ? MoveType.MCC : MoveType.MC;
                    break;
                case "e":
                    mt = event.isShiftDown() ? MoveType.ECC : MoveType.EC;
                    break;
                case "s":
                    mt = event.isShiftDown() ? MoveType.SCC : MoveType.SC;
                    break;
                case "backspace":
                    resetCube = true;
                    break;
                default:
                    mt = null;
                    break;
            }
            if(mt != null) {
                _cube.move(mt);
            }else if(resetCube){
                _cube.reset();
            }
        }
    }
}
