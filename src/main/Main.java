
package main;

import frames.FrmGame;
import utils.Options;
import utils.Sprites;

public class Main {

    public static void main(String[] args) {
        Options.init();
        Sprites.init();
        FrmGame game = new FrmGame();
    }
    
}
