/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frames;

import entitities.Player;
import handlers.KeyHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import managers.GameManager;
import managers.HexagonManager;
import managers.PlayerManager;
import utils.ColorRandomizer;
import utils.Hexagon;
import utils.SpriteSheet;
import utils.Sprites;
import managers.SpritesManager;
import utils.GameState;

public class GamePanel extends JPanel {

    public Timer gameTimer;
    public FrmGame frmGame;
    Color backgroundColor;
    int background_index;
    public SpritesManager spritesManager;
    public PlayerManager playerManager;
    public HexagonManager hexagonManager;
    public GameManager gameManager;
    public KeyHandler keyHandler;

    public GamePanel(FrmGame frmGame) {
        this.frmGame = frmGame;
        frmGame.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        frmGame.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setFocusable(true);
        this.requestFocus();
        this.setDoubleBuffered(true);
        resetAll();
    }

    public void update() {
        gameManager.update();
        hexagonManager.update();
        playerManager.update();
    }

    public void resetAll() {

        if (gameTimer != null) {
            gameTimer.cancel();
        }

        gameTimer = new Timer();

        backgroundColor = new Color(46, 56, 82);
        this.setBackground(backgroundColor);
        background_index = 0;
        gameManager = new GameManager(this);
        hexagonManager = new HexagonManager(this);
        spritesManager = new SpritesManager();
        playerManager = new PlayerManager(this);

        keyHandler = new KeyHandler(this);
        frmGame.addKeyListener(keyHandler);

        SpriteSheet backgroundSP = new SpriteSheet(10, Sprites.BACKGROUND_SPRITESHEET, 0, 0, 0);

        //Player player1 = new Player(this, 450, 200);
        //Player player2 = new Player(650, 200);
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                repaint();
            }
        }, 0, 17);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, frmGame.screenWidth, frmGame.screenHeight);
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.addRenderingHints(rh);

        spritesManager.draw(g2);

        g2.dispose();

    }

    public void keyPressed(int keyCode) {
        if (gameManager.gameState != GameState.MENU
                && gameManager.gameState != GameState.RESTARTING
                && gameManager.gameState != GameState.STARTING
                && gameManager.gameState != GameState.SPAWNING_HEXAGONS
                && gameManager.gameState != GameState.SPAWNING_PLAYERS
                && gameManager.gameState != GameState.ENDING) {
            playerManager.keyPressed(keyCode);
        }
    }

    public void keyReleased(int keyCode) {

        if (Integer.compare(KeyEvent.VK_ESCAPE, keyCode) == 0) {
            frmGame.dispose();
            System.exit(0);
        }
        if (gameManager.gameState != GameState.MENU
                && gameManager.gameState != GameState.RESTARTING
                && gameManager.gameState != GameState.STARTING
                && gameManager.gameState != GameState.SPAWNING_HEXAGONS
                && gameManager.gameState != GameState.SPAWNING_PLAYERS
                && gameManager.gameState != GameState.ENDING) {
            playerManager.keyReleased(keyCode);
        }
    }

}
