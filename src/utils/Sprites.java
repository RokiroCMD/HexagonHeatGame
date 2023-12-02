package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Sprites {

    public static List<BufferedImage> BACKGROUND_SPRITESHEET = new ArrayList<>();
    
    public static List<BufferedImage> MARIO_LEFT = new ArrayList<>();
    public static List<BufferedImage> MARIO_RIGHT = new ArrayList<>();
    public static List<BufferedImage> MARIO_UP = new ArrayList<>();
    public static List<BufferedImage> MARIO_DOWN = new ArrayList<>();
    public static List<BufferedImage> MARIO_DEAD = new ArrayList<>();
    
    public static List<BufferedImage> PEACH_UP = new ArrayList<>();
    public static List<BufferedImage> PEACH_DOWN = new ArrayList<>();
    public static List<BufferedImage> PEACH_LEFT = new ArrayList<>();
    public static List<BufferedImage> PEACH_RIGHT = new ArrayList<>();
    public static List<BufferedImage> PEACH_DEAD = new ArrayList<>();
    
    public static List<BufferedImage> MALLOW_UP = new ArrayList<>();
    public static List<BufferedImage> MALLOW_DOWN = new ArrayList<>();
    public static List<BufferedImage> MALLOW_LEFT = new ArrayList<>();
    public static List<BufferedImage> MALLOW_RIGHT = new ArrayList<>();
    public static List<BufferedImage> MALLOW_DEAD = new ArrayList<>();
    
    public static List<BufferedImage> GENO_UP = new ArrayList<>();
    public static List<BufferedImage> GENO_DOWN = new ArrayList<>();
    public static List<BufferedImage> GENO_LEFT = new ArrayList<>();
    public static List<BufferedImage> GENO_RIGHT = new ArrayList<>();
    public static List<BufferedImage> GENO_DEAD = new ArrayList<>();
 
    
    public static void init() {

        for (int i = 1; i <= 5; i++) {
            try {
                BufferedImage tempImage = loadSpriteImage("src/resources/sprites/backgrounds/background_0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, Options.screenWidth, Options.screenHeight);
                BACKGROUND_SPRITESHEET.add(tempImage);

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sprites.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Sprites.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        for (int i = 1; i <= 4; i++) {
            try {
                BufferedImage tempImage = loadSpriteImage("src/resources/sprites/entities/mario/mario_left0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MARIO_LEFT.add(tempImage);
                tempImage = flipSpriteImage(tempImage);
                MARIO_RIGHT.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/mario/mario_up0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MARIO_UP.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/mario/mario_down0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MARIO_DOWN.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/peach/peach_up0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                PEACH_UP.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/peach/peach_down0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                PEACH_DOWN.add(tempImage);
                PEACH_LEFT.add(tempImage);
                
                tempImage = flipSpriteImage(tempImage);
                PEACH_RIGHT.add(tempImage);
                
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/mallow/mallow_up0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MALLOW_UP.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/mallow/mallow_down0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MALLOW_DOWN.add(tempImage);
                MALLOW_LEFT.add(tempImage);
                
                tempImage = flipSpriteImage(tempImage);
                MALLOW_RIGHT.add(tempImage);
                
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/geno/geno_up0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                GENO_UP.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/geno/geno_down0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                GENO_DOWN.add(tempImage);
                GENO_LEFT.add(tempImage);
                
                tempImage = flipSpriteImage(tempImage);
                GENO_RIGHT.add(tempImage);
                
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sprites.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Sprites.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        for (int i = 1; i <= 2; i++) {
            try {
                BufferedImage tempImage = loadSpriteImage("src/resources/sprites/entities/mario/mario_dead0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MARIO_DEAD.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/peach/peach_dead0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                PEACH_DEAD.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/mallow/mallow_dead0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                MALLOW_DEAD.add(tempImage);
                
                tempImage = loadSpriteImage("src/resources/sprites/entities/geno/geno_dead0" + i + ".png");
                tempImage = rescaleSpriteImage(tempImage, 64, 64);
                GENO_DEAD.add(tempImage);
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sprites.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Sprites.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static BufferedImage loadSpriteImage(String ruta) throws FileNotFoundException, IOException {
        BufferedImage tempImage = ImageIO.read(new FileInputStream(ruta));
        return tempImage;
    }

    public static BufferedImage rescaleSpriteImage(BufferedImage tempImage, int width, int height) {
        ImageIcon tempImageIcon = new ImageIcon(tempImage);
        Image image = tempImageIcon.getImage().getScaledInstance(width,height, Image.SCALE_AREA_AVERAGING);
        tempImage = new BufferedImage(
                image.getWidth(null),
                image.getWidth(null),
                BufferedImage.TYPE_INT_ARGB
        );
        tempImage.getGraphics().drawImage(image, 0, 0, null);
        return tempImage;
    }
    
    public static BufferedImage flipSpriteImage(BufferedImage tempImage){
        int width = tempImage.getWidth();
        int height = tempImage.getHeight();

        BufferedImage nuevaImagen = new BufferedImage(width, height, tempImage.getType());
        
        Graphics2D g2d = nuevaImagen.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance(-1, 1);
        at.translate(-width, 0);
        g2d.setTransform(at);
        g2d.drawImage(tempImage, 0, 0, null);
        g2d.dispose();

        return nuevaImagen;
    }
    

}
