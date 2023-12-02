
package managers;

import frames.GamePanel;
import utils.GameState;

public class GameManager {
    
    public GameState gameState = GameState.STARTING;
    private GamePanel gp;
    private long nextTiming = 2 *1000l;
    private int actionCount = 0;

    public GameManager(GamePanel gp) {
        this.gp = gp;
    }
    
    
    
    public void update(){
        switch (gameState) {
            case MENU:
                break;
            case RESTARTING:
                gp.resetAll();
                System.out.println("No se detuvo");
                break;
            case STARTING:
                
                gp.hexagonManager.createGridOffScreen();
                
                nextTiming = (long) (System.currentTimeMillis() + 10 * 1000);
                
                gameState = GameState.SPAWNING_HEXAGONS;
                
                break;
            case SPAWNING_HEXAGONS:
                
                if(!gp.hexagonManager.isInCenterOfScreen()){
                    gp.hexagonManager.translateHexagons(0,3);
                } else{
                    nextTiming = 0;
                }
                
                if (System.currentTimeMillis() > nextTiming) {
                    nextTiming = System.currentTimeMillis() + 1 * 1000;
                    gameState =GameState.SPAWNING_PLAYERS; 
                } 
                
                
                break;
            case SPAWNING_PLAYERS:
                if (System.currentTimeMillis() > nextTiming) {
                    nextTiming = System.currentTimeMillis() + 1 * 1000;
                    gp.playerManager.addPlayer();
                    if (gp.playerManager.totalPlayers() >= 4) {
                        nextTiming = System.currentTimeMillis() + 5 * 1000;
                        gameState =GameState.SELECTING_COLOR; 
                    }
                }
                
                
                
                break;
            case SELECTING_COLOR:
                
                break;
            case DROPPING_HEXAGONS:
                
                break;
            case WAITING:
                
                break;
            case REGENERATING_HEXAGONS:
                
                break;
            case ENDING:
                
                break;
        }
    }
    
}
