/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author alexc
 */
public class GamePanel extends JPanel{

    Timer gameTimer;
    FrmGame frmGame;
    
    public GamePanel(FrmGame frmGame) {
        this.frmGame = frmGame;
        this.setBackground(Color.red);
        
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            
                repaint();
            }
        }, 17);
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
    
    
    
    
    
}
