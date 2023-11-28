package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class FrmGame extends JFrame {

    public int screenWidth = 900;
    public int screenHeight = 600;
    
    public FrmGame() {
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setLocation(this.getLocation().x - screenWidth /2, this.getLocation().y - screenHeight/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension d = new Dimension(screenWidth, screenHeight);
        this.setSize(d);
        
        
        this.setExtendedState(MAXIMIZED_BOTH);
        
    }
    
    
    
}
