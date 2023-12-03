package managers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import utils.Hexagon;
import utils.SpriteSheet;
import utils.Sprites;

public class SpritesManager {

    public static List<SpriteSheet> sprites = new ArrayList<>();
    public static List<Hexagon> hexagons = new ArrayList<>();
    public SpritesManager() {

    }

    public static void setHexagonos(List<Hexagon> hexagonos) {
        SpritesManager.hexagons = hexagonos;
    }
    
    public void reset(){
        sprites = new ArrayList<>();
        hexagons = new ArrayList<>();
    }
    
    
    
    public void draw(Graphics2D g2) {
        drawCapes(g2, 0);
        drawCapes(g2, 1);
        drawHexagons(g2);
        drawCapes(g2, 2);
    }
    
    public void drawCapes(Graphics2D g2,int i){
        for (SpriteSheet sprite : sprites) {
            if (sprite.layer != i){
                continue;
            } else{
                if (sprite.nextFrameTime < System.currentTimeMillis()) {
                sprite.nextFrameTime = System.currentTimeMillis() + (1000 / sprite.fps);
                sprite.index++;
                if (sprite.index > sprite.frames.size() - 1) {
                    sprite.index = 0;
                }
            }
                g2.drawImage(sprite.frames.get(sprite.index), null, sprite.x, sprite.y);
            }
        }
    }
    
    public void drawHexagons(Graphics2D g2){
        for (Hexagon h : hexagons) {
            
            g2.setColor(h.getShadowColor());
            g2.fill(h.getShadowPolygon());
            
            g2.setColor(h.getColor());
            g2.fill(h.getPolygon());
            
            g2.setColor(h.getColor());
            g2.fill(h.getPolygon());
            g2.setColor(Color.BLACK);
            g2.draw(h.getPolygon());
        }
    }
}
