package managers;

import entitities.Player;
import frames.GamePanel;
import java.awt.Color;
import utils.GameState;
import utils.Sounds;

public class GameManager {

    public GameState gameState = GameState.STARTING;
    private GamePanel gp;
    private long nextTiming = 2 * 1000l;
    private int actionCount = 0;
    public Color selectedColor;
    public Player winner;
    public int difficulty = 0;
    public int maxDifficulty = 18;

    public GameManager(GamePanel gp) {
        this.gp = gp;
    }

    public void update() {
        
        if (gameState == GameState.WAITING ||
                gameState == GameState.REGENERATING_HEXAGONS ||
                gameState == GameState.DROPPING_HEXAGONS ||
                gameState == GameState.SHOWING_COLOR ||
                gameState == GameState.SELECTING_COLOR) {
            if(PlayerManager.alive_players.size() <= 1){
                nextTiming = (long) (System.currentTimeMillis() + 4.5 * 1000);
                if (PlayerManager.alive_players.size()<1 ) {
                    Sounds.reproduceOnce(Sounds.DRAW_SOUND);
                    winner = null;
                } else{
                    winner = PlayerManager.alive_players.get(0);
                    Sounds.reproduceOnce(Sounds.WIN_SOUND);
                }
                gp.stopMusic();
                
                gameState = gameState.SHOWWING_WINNER;
            }
        }
        
        switch (gameState) {
            case MENU:
                break;
            case RESTARTING:
                Sounds.reproduceOnce(Sounds.RETART_SOUND);
                gp.resetAll();
                gameState = GameState.STARTING;
                break;
            case STARTING:

                gp.hexagonManager.createGridOffScreen();

                nextTiming = (long) (System.currentTimeMillis() + 10 * 1000);
                gp.startMusc();
                gameState = GameState.SPAWNING_HEXAGONS;

                break;
            case SPAWNING_HEXAGONS:

                if (!gp.hexagonManager.isInCenterOfScreen()) {
                    gp.hexagonManager.translateHexagons(0, 3);
                } else {
                    nextTiming = 0;
                }

                if (System.currentTimeMillis() > nextTiming) {
                    nextTiming = System.currentTimeMillis() + 1 * 1000;
                    gameState = GameState.SPAWNING_PLAYERS;
                }

                break;
            case SPAWNING_PLAYERS:
                if (System.currentTimeMillis() > nextTiming) {
                    nextTiming = System.currentTimeMillis() + 1 * 1000;
                    gp.playerManager.addPlayer();
                    Sounds.reproduceOnce(Sounds.SPAWN_PLAYER_SOUND);
                    if (gp.playerManager.totalPlayers() >= gp.numPlayers) {
                        nextTiming = System.currentTimeMillis() + 3 * 1000;
                        gameState = GameState.SELECTING_COLOR;
                    }
                }

                break;
            case SELECTING_COLOR:
                if (System.currentTimeMillis() > nextTiming) {
                    int indexColor = (int) (Math.random() * gp.hexagonManager.hexagonColors.size());
                    selectedColor = gp.hexagonManager.hexagonColors.get(indexColor);
                    Sounds.reproduceOnce(Sounds.SHOW_FLAG_SOUND);
                    nextTiming = System.currentTimeMillis() + ((3 * 1000) - (difficulty * 150));
                    gameState = GameState.SHOWING_COLOR;
                }
                break;
                case SHOWING_COLOR:
                    if (System.currentTimeMillis() > nextTiming) {
                        nextTiming = (long) (System.currentTimeMillis() + 2.5 * 1000);
                        gameState = GameState.DROPPING_HEXAGONS;
                        Sounds.reproduceOnce(Sounds.DROPPING_HEXAGONS_SOUND);
                    }
                break;
            case DROPPING_HEXAGONS:
                if (System.currentTimeMillis() >= nextTiming) {
                    nextTiming = (long) (System.currentTimeMillis() + 1.5 * 1000);
                    gameState = GameState.WAITING;
                } else {
                    gp.hexagonManager.dropHexagons(selectedColor);
                }

                break;
            case WAITING:
                if (System.currentTimeMillis() >= nextTiming) {
                    nextTiming = System.currentTimeMillis() + 1 * 1000;
                    gp.hexagonManager.regenerateHexgons();
                    gameState = GameState.REGENERATING_HEXAGONS;
                    Sounds.reproduceOnce(Sounds.SPAWNED_ISLANDS_SOUND);
                }
                break;
            case REGENERATING_HEXAGONS:
                if (System.currentTimeMillis() >= nextTiming) {
                    nextTiming = (long) (System.currentTimeMillis() + ((2.5 * 1000) - (difficulty * 100) ) );
                    gameState = GameState.SELECTING_COLOR;
                    difficulty ++;
                    if (difficulty  > maxDifficulty) {
                        difficulty = maxDifficulty;
                    }
                }
                break;
            case SHOWWING_WINNER:
                if (System.currentTimeMillis() >= nextTiming) {
                    nextTiming = 0;
                    gameState = GameState.RESTARTING;
                }
                break;
            case ENDING:

                break;
        }
    }

}
