/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frames;

import handlers.KeyHandler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import managers.GameManager;
import managers.HexagonManager;
import managers.PlayerManager;
import utils.SpriteSheet;
import utils.Sprites;
import managers.SpritesManager;
import utils.Fonts;
import utils.GameState;
import utils.Sounds;

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
    Font winnerFont;
    public int numPlayers = 1;
    public Clip music;

    public GamePanel(FrmGame frmGame, int numPlayers) {
        this.frmGame = frmGame;
        frmGame.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        frmGame.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setFocusable(true);
        this.requestFocus();
        this.setDoubleBuffered(true);

        this.setBackground(backgroundColor);
        keyHandler = new KeyHandler(this);
        frmGame.addKeyListener(keyHandler);
        this.numPlayers = numPlayers;
        this.gameManager = new GameManager(this);
        music = Sounds.reproduceRemotely(Sounds.GAME_MUSIC);
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
        winnerFont = Fonts.NEW_SUPER_MARIO_FONT.deriveFont(Font.PLAIN, 128f);
        backgroundColor = new Color(46, 56, 82);
        background_index = 0;
        hexagonManager = new HexagonManager(this);
        hexagonManager.reset();
        spritesManager = new SpritesManager();
        spritesManager.reset();
        playerManager = new PlayerManager(this);
        playerManager.reset();
        gameManager.difficulty = 0;
        
        SpriteSheet backgroundSP = new SpriteSheet(8, Sprites.BACKGROUND_SPRITESHEET, 0, 0, background_index);

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

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        spritesManager.draw(g2);

        if (gameManager.gameState.equals(GameState.SHOWING_COLOR)) {
            g2.setColor(Color.BLACK);
            g2.fillRect(frmGame.screenWidth - 500, frmGame.getHeight() / 2 - 260, 480, 220);
            g2.setColor(gameManager.selectedColor);
            g2.fillRect(frmGame.screenWidth - 485, frmGame.getHeight() / 2 - 250, 450, 200);
            
            g2.setFont(winnerFont.deriveFont(Font.PLAIN, 128f));
            g2.setColor(Color.BLACK);
            g2.drawString("COLOR", frmGame.screenWidth - 469, frmGame.getHeight() / 2 - 280);
        }

        if (gameManager.gameState == GameState.SHOWWING_WINNER) {
            g2.setFont(winnerFont);

            String text = "";
            int x = 0;
            int y = 0;
            int textWidth = 0;

            if (gameManager.winner != null) {
                text = gameManager.winner.getName() + " GANA";
                textWidth = winnerFont.getSize() * text.length();
                x = (frmGame.screenWidth / 2 - textWidth / 2) + 200;
                y = frmGame.screenHeight / 2;
                g2.setColor(Color.ORANGE);
            } else {
                text = "EMPATE";
                textWidth = winnerFont.getSize() * text.length();
                x = (frmGame.screenWidth / 2 - textWidth / 2) + 200;
                y = frmGame.screenHeight / 2;
                g2.setColor(Color.GRAY);
            }

            g2.drawString(text, x, y);

        }
        
            g2.setColor(Color.BLACK);
            g2.fillRect(10, 15, 320, 60);
            
            
            g2.setFont(winnerFont.deriveFont(Font.PLAIN, 48));
            
            Color difficultyColor = Color.WHITE;
            
            if (gameManager.difficulty >= 10 ) {
                difficultyColor = Color.YELLOW;
                if (gameManager.difficulty >= 15) {
                    difficultyColor = Color.RED;
                    if (gameManager.difficulty >= gameManager.maxDifficulty) {
                        difficultyColor = new Color(86, 43, 179);
                    }
                }
            }
            
            g2.setColor(difficultyColor);
                        
            g2.drawString("Dificultad: " + gameManager.difficulty, 20, 60);

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
            exitGame();

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

    public void stopMusic() {
        music.stop();
        music.flush();
        music = Sounds.reproduceRemotely(Sounds.GAME_MUSIC);
    }

    public void exitGame() {
        FrmMenu frmMenu = new FrmMenu();
        frmMenu.setVisible(true);
        frmMenu.setLocationRelativeTo(null);
        stopMusic();
        this.removeAll();
        gameTimer.cancel();
        frmGame.dispose();
    }

    public void startMusc() {
        music.loop(Clip.LOOP_CONTINUOUSLY);
        music.start();
    }

}
