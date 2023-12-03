
package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Fonts {
    
    public static Font NEW_SUPER_MARIO_FONT;
    
    public static void InitFonts(){
        try {
            NEW_SUPER_MARIO_FONT = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/resources/fonts/New_Super_Mario_Font_U.ttf"));
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fonts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(Fonts.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Fonts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
