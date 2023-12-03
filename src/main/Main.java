
package main;

import frames.FrmGame;
import frames.FrmMenu;
import utils.Fonts;
import utils.Options;
import utils.Sprites;

public class Main {

    public static void main(String[] args) {
        Fonts.InitFonts();
        Options.init();
        Sprites.init();
        FrmMenu frmMenu = new FrmMenu();
        frmMenu.setVisible(true);
        frmMenu.toFront();
        frmMenu.setLocationRelativeTo(null);
        //FrmGame game = new FrmGame(numJugadores);
    }
    
}
