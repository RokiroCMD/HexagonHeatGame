
package handlers;

import frames.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener{

    GamePanel gp;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gp.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gp.keyReleased(e.getKeyCode());
    }
    
}
