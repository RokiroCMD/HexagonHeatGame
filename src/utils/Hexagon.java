package utils;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import managers.HexagonManager;

public class Hexagon {

    Color color;
    Color shadowColor;
    Polygon polygon;
    Polygon shadowPolygon;
    BufferedImage texture;
    int y = 0;
    int x =0;
            
    public Hexagon(Color color, Polygon polygon) {
        this.color = color;
        this.polygon = polygon;
        shadowPolygon = new Polygon(polygon.xpoints, polygon.ypoints, 6);
        shadowPolygon.translate(0, 60);
        double factorOscurecimiento = 0.5;
        shadowColor = darkenColor(color, factorOscurecimiento);
        
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public Polygon getShadowPolygon() {
        return shadowPolygon;
    }

    public void translatePolygon(int x, int y) {
        polygon.translate(x, y);
        shadowPolygon.translate(x, y);
    }

    public void shrinkPolygon(double factorAchicamiento) {
        try {
            int[] xPoints = polygon.xpoints.clone();
            int[] yPoints = polygon.ypoints.clone();

            int centerX = polygon.getBounds().x + polygon.getBounds().width / 2;
            int centerY = polygon.getBounds().y + polygon.getBounds().height / 2;

            for (int i = 0; i < xPoints.length; i++) {
                int deltaX = xPoints[i] - centerX;
                int deltaY = yPoints[i] - centerY;

                // Calcula la nueva posición más cercana al centro
                xPoints[i] = centerX + (int) (deltaX * (1 - factorAchicamiento));
                yPoints[i] = centerY + (int) (deltaY * (1 - factorAchicamiento));
            }
            polygon = new Polygon(xPoints, yPoints, polygon.npoints);
            shadowPolygon = new Polygon(xPoints, yPoints, polygon.npoints);
            shadowPolygon.translate(0, 60);
            
            if (polygon.getBounds().getSize().width < 2) {
                HexagonManager.addToRemove(this);
            }
            
        } catch (Exception e) {

        }
    }

    public static Color darkenColor(Color colorOriginal, double factorOscurecimiento) {
        int red = (int) (colorOriginal.getRed() * (1 - factorOscurecimiento));
        int green = (int) (colorOriginal.getGreen() * (1 - factorOscurecimiento));
        int blue = (int) (colorOriginal.getBlue() * (1 - factorOscurecimiento));

        // Asegurarse de que los valores estén en el rango [0, 255]
        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));

        return new Color(red, green, blue);
    }

}
