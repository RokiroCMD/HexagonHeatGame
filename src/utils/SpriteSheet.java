
package utils;

import managers.SpritesManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    
    public int fps;
    public List<BufferedImage> frames = new ArrayList<>();
    public Long nextFrameTime = 0l;
    public int index =0;
    public int layer = 0;
    public int x=0;
    public int y=0;
    
    public SpriteSheet(int fps, List<BufferedImage> frames, int x, int y, int layer ) {
        this.fps = fps;
        this.frames = frames;
        this.x = x;
        this.y = y;
        this.layer = layer;
        SpritesManager.sprites.add(this);
    }
    
    

    
    
    
    
}
