
package utils;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ColorRandomizer {

    public static List<Color> colors = Arrays.asList(
    //Color Rojo
    new Color(232, 21, 49),
    //Color Amarillo
    new Color(251, 255, 38),
    // Color Azul Rey
    new Color(46, 4, 184),
    //Color Rosa
    new Color(255, 59, 173),
    //Color lima
    new Color(22, 245, 85),
    //Color naranja
    new Color(255, 124, 31),
    //Color café
    new Color(166, 89, 38),
    // Color Ceslesto
    new Color(28, 251, 255),
    // Color Morado
    new Color(126, 0, 189),
    // Color gris
    new Color(46, 37, 48)
    );  
    public static Random random = new Random();
    
    public static Color pickColor(){
        int size = colors.size();
        return colors.get(random.nextInt(0,size));
    }
    
}
