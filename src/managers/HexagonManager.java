package managers;

import frames.GamePanel;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import utils.ColorRandomizer;
import utils.Hexagon;

public class HexagonManager {

    public List<Hexagon> hexagons = new ArrayList<>();
    public List<Color> hexagonColors = new ArrayList<>();
    GamePanel gp;
    public List<Hexagon> droppingHexagons = new ArrayList<>();
    private static List<Hexagon> removingHexagons = new ArrayList<>();
    public static int actualXGrid = 0;
    public static int actualYGrid = 0;

    int xOffset = 0;
    int yOffset = 0;
    int width = 27;
    int gridSize = 2;
    int gridWidht = 0;
    int gridHeight = 0;
    public int centerScreenY;

    public HexagonManager(GamePanel gp) {
        this.gp = gp;
        centerScreenY = (gp.frmGame.screenHeight / 2) - (gridHeight / 2);
        hexagons = new ArrayList<>();
        hexagonColors = new ArrayList<>();
        //createGrid();
    }

    public void update() {
        /*if (!hexagons.isEmpty()) {    
            hexagons.get(0).shrinkPolygon(0.005);
        }*/
        removeStoredHexagons();
    }

    public boolean isInsideHexagon(Rectangle rectangle) {
        for (Hexagon h : hexagons) {
            if (h.getPolygon().intersects(rectangle)) {
                return true;
            }
        }
        return false;
    }

    public Color pickRandomColor() {
        boolean colorValido = false;
        while (!colorValido) {
            Color tempColor = ColorRandomizer.pickColor();
            if (!hexagonColors.contains(tempColor)) {
                colorValido = true;
                hexagonColors.add(tempColor);
                return tempColor;
            }
        }
        return Color.WHITE;
    }

    public void createGrid() {
        gridWidht = (gridSize + 1) * (width * hexagonWidth);
        gridHeight = (gridSize + 1) * (width * hexagonHeight);

        xOffset = (gp.frmGame.screenWidth / 2) - (gridWidht / 2);
        yOffset = (gp.frmGame.screenHeight / 2) - (gridHeight / 2);

        actualYGrid = yOffset;
        actualXGrid = xOffset;
        for (int i = 1; i <= gridSize; i++) {
            hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + (hexagonWidth * width * i) - ((hexagonWidth * width) / 2), yOffset, width)));
        }
        for (int i = 0; i <= gridSize; i++) {
            if (i == 1) {
                hexagons.add(new Hexagon(Color.WHITE, createPolygon(xOffset + ((hexagonWidth / 2) * width) + (hexagonWidth * width * i) - (hexagonWidth * width / 2), yOffset + ((hexagonHeight * (width)) - (2 * width)), width)));
            } else {
                hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + ((hexagonWidth / 2) * width) + (hexagonWidth * width * i) - (hexagonWidth * width / 2), yOffset + ((hexagonHeight * (width)) - (2 * width)), width)));

            }
        }
        for (int i = 1; i <= gridSize; i++) {
            hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + (hexagonWidth * width * i) - ((hexagonWidth * width) / 2), yOffset + (((hexagonHeight * width) - (2 * width)) * 2), width)));
        }

        //hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + (hexagonWidth * width * 4) - ((hexagonWidth * width) / 2), yOffset, width)));
        SpritesManager.setHexagonos(hexagons);
    }

    public void createGridOffScreen() {
        gridWidht = (gridSize + 1) * (width * hexagonWidth);
        gridHeight = (gridSize + 1) * (width * hexagonHeight);

        xOffset = (gp.frmGame.screenWidth / 2) - (gridWidht / 2);
        yOffset = -gridHeight;
        actualYGrid = yOffset;
        actualXGrid = xOffset;

        for (int i = 1; i <= gridSize; i++) {
            hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + (hexagonWidth * width * i) - ((hexagonWidth * width) / 2), yOffset, width)));
        }
        for (int i = 0; i <= gridSize; i++) {
            if (i == 1) {
                hexagons.add(new Hexagon(Color.WHITE, createPolygon(xOffset + ((hexagonWidth / 2) * width) + (hexagonWidth * width * i) - (hexagonWidth * width / 2), yOffset + ((hexagonHeight * (width)) - (2 * width)), width)));
            } else {
                hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + ((hexagonWidth / 2) * width) + (hexagonWidth * width * i) - (hexagonWidth * width / 2), yOffset + ((hexagonHeight * (width)) - (2 * width)), width)));

            }
        }
        for (int i = 1; i <= gridSize; i++) {
            hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + (hexagonWidth * width * i) - ((hexagonWidth * width) / 2), yOffset + (((hexagonHeight * width) - (2 * width)) * 2), width)));
        }

        //hexagons.add(new Hexagon(pickRandomColor(), createPolygon(xOffset + (hexagonWidth * width * 4) - ((hexagonWidth * width) / 2), yOffset, width)));
        SpritesManager.setHexagonos(hexagons);
    }

    public int hexagonWidth = 10;
    public int hexagonHeight = 9;

    public Polygon createPolygon(int x, int y, int width) {
        int xpoints[] = {
            x + (0 * width),
            x + (5 * width),
            x + (hexagonWidth * width),
            x + (hexagonWidth * width),
            x + (5 * width),
            x + (0 * width)};
        int ypoints[] = {
            y + (2 * width),
            y + (0 * width),
            y + (2 * width),
            y + (7 * width),
            y + (hexagonHeight * width),
            y + (7 * width)};
        Polygon polygon = new Polygon(xpoints, ypoints, 6);
        return polygon;
    }

    public static void addToRemove(Hexagon hexagon) {
        if (!removingHexagons.contains(hexagon)) {
            removingHexagons.add(hexagon);
        }
    }

    public int[] centerPositionHexagon(int id) {
        Polygon polygon = hexagons.get(id).getPolygon();
        int centerX = polygon.getBounds().x + polygon.getBounds().width / 2;
        int centerY = polygon.getBounds().y + polygon.getBounds().height / 2;
        return new int[]{centerX, centerY};
    }

    public boolean isInCenterOfScreen() {
          return  actualYGrid >= (gp.frmGame.screenHeight / 2) - (gridHeight / 2);
    }

    private void removeStoredHexagons() {

        if (removingHexagons.isEmpty()) {
            return;
        }

        while (!removingHexagons.isEmpty()) {
            Hexagon removedHexagon = null;

            for (Hexagon h : hexagons) {
                if (removingHexagons.contains(h)) {
                    removedHexagon = h;
                    break;
                }
            }
            try {
                if (hexagons.contains(removedHexagon)) {
                    hexagons.remove(removedHexagon);
                }
                if (droppingHexagons.contains(removedHexagon)) {
                    droppingHexagons.remove(removedHexagon);
                }
                if (removingHexagons.contains(removedHexagon)) {
                    removingHexagons.remove(removedHexagon);
                }
            } catch (Exception e) {
            }

        }

    }

    void translateHexagons(int x, int y) {
        actualYGrid += y;
        actualXGrid += x;
        for (Hexagon h : hexagons) {
            h.translatePolygon(x, y);
        }
    }

}
