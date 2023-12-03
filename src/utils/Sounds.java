
package utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sounds {

    
    public static String DRAW_SOUND = "src/resources/sounds/draw.wav";
    public static String DROPPING_HEXAGONS_SOUND = "src/resources/sounds/dropping_hexagons.wav";
    public static String LETSGO_MARIO_SOUND = "src/resources/sounds/letsgo_mario.wav";
    public static String RETART_SOUND = "src/resources/sounds/restart.wav";
    public static String RETURN_SOUND = "src/resources/sounds/return.wav";
    public static String SELECT_SOUND = "src/resources/sounds/select.wav";
    public static String SHOW_FLAG_SOUND = "src/resources/sounds/show_flag.wav";
    public static String SPAWN_PLAYER_SOUND = "src/resources/sounds/spawn_player.wav";
    public static String SPAWNED_ISLANDS_SOUND = "src/resources/sounds/spawned_islands.wav";
    public static String START_GAME_SOUND = "src/resources/sounds/start_game.wav";
    public static String WIN_SOUND = "src/resources/sounds/win.wav";
    public static String PLAYER_FALL_SOUND = "src/resources/sounds/player_fall.wav";
    public static String MENU_MUSIC = "src/resources/sounds/menu_music.wav";
    public static String GAME_MUSIC = "src/resources/sounds/game_music.wav";
    
    
    public static void reproduceOnce(String ruta){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Clip reproduceRemotely(String ruta){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Sounds.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
