package utils;

import java.awt.Toolkit;

public class Options {

    public static int screenWidth;
    public static int screenHeight;
    
    public static void init(){
        
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
}
