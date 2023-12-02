package managers;

import entitities.Player;
import frames.GamePanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.GameState;
import utils.PlayerState;

public class PlayerManager {

    public static List<Player> players = new ArrayList<>();
    public List<Integer> keyStrokesJ1 = new ArrayList<>();
    public List<Integer> keyStrokesJ2 = new ArrayList<>();
    public List<Integer> keyStrokesJ3 = new ArrayList<>();
    public List<Integer> keyStrokesJ4 = new ArrayList<>();

    GamePanel gamePanel;

    public PlayerManager(GamePanel gamePanel) {
        this.keyStrokesJ1 = Arrays.asList(
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D
        );
        this.keyStrokesJ2 = Arrays.asList(
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT
        );
        this.keyStrokesJ3 = Arrays.asList(
                KeyEvent.VK_I,
                KeyEvent.VK_K,
                KeyEvent.VK_J,
                KeyEvent.VK_L
        );
        this.keyStrokesJ4 = Arrays.asList(
                KeyEvent.VK_NUMPAD8,
                KeyEvent.VK_NUMPAD5,
                KeyEvent.VK_NUMPAD4,
                KeyEvent.VK_NUMPAD6
        );
        this.gamePanel = gamePanel;
    }

    public void update() {
        for (Player player : players) {
            if (player.playerState != PlayerState.DEAD) {
                player.update();
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (Player player : players) {
            if (player.playerState != PlayerState.DEAD) {
                player.draw(g2);
            }
        }
    }

    public void keyPressed(Integer keyCode) {

        if (keyStrokesJ1.contains(keyCode)) {

            if (keyStrokesJ1.get(0).equals(keyCode)) {
                players.get(0).upKey = true;
            } else if (keyStrokesJ1.get(1).equals(keyCode)) {
                players.get(0).downKey = true;
            } else if (keyStrokesJ1.get(2).equals(keyCode)) {
                players.get(0).leftKey = true;
            } else if (keyStrokesJ1.get(3).equals(keyCode)) {
                players.get(0).rightKey = true;
            }

        } else if (keyStrokesJ2.contains(keyCode)) {
            if (keyStrokesJ2.get(0).equals(keyCode)) {
                players.get(1).upKey = true;
            } else if (keyStrokesJ2.get(1).equals(keyCode)) {
                players.get(1).downKey = true;
            } else if (keyStrokesJ2.get(2).equals(keyCode)) {
                players.get(1).leftKey = true;
            } else if (keyStrokesJ2.get(3).equals(keyCode)) {
                players.get(1).rightKey = true;
            }
        } else if (keyStrokesJ3.contains(keyCode)) {
            if (keyStrokesJ3.get(0).equals(keyCode)) {
                players.get(2).upKey = true;
            } else if (keyStrokesJ3.get(1).equals(keyCode)) {
                players.get(2).downKey = true;
            } else if (keyStrokesJ3.get(2).equals(keyCode)) {
                players.get(2).leftKey = true;
            } else if (keyStrokesJ3.get(3).equals(keyCode)) {
                players.get(2).rightKey = true;
            }
        } else if (keyStrokesJ4.contains(keyCode)) {
            if (keyStrokesJ4.get(0).equals(keyCode)) {
                players.get(3).upKey = true;
            } else if (keyStrokesJ4.get(1).equals(keyCode)) {
                players.get(3).downKey = true;
            } else if (keyStrokesJ4.get(2).equals(keyCode)) {
                players.get(3).leftKey = true;
            } else if (keyStrokesJ4.get(3).equals(keyCode)) {
                players.get(3).rightKey = true;
            }
        }

    }

    public void keyReleased(int keyCode) {
        if (keyStrokesJ1.contains(keyCode)) {
            if (keyStrokesJ1.get(0).equals(keyCode)) {
                players.get(0).upKey = false;
            } else if (keyStrokesJ1.get(1).equals(keyCode)) {
                players.get(0).downKey = false;
            } else if (keyStrokesJ1.get(2).equals(keyCode)) {
                players.get(0).leftKey = false;
            } else if (keyStrokesJ1.get(3).equals(keyCode)) {
                players.get(0).rightKey = false;
            }

        } else if (keyStrokesJ2.contains(keyCode)) {
            if (keyStrokesJ2.get(0).equals(keyCode)) {
                players.get(1).upKey = false;
            } else if (keyStrokesJ2.get(1).equals(keyCode)) {
                players.get(1).downKey = false;
            } else if (keyStrokesJ2.get(2).equals(keyCode)) {
                players.get(1).leftKey = false;
            } else if (keyStrokesJ2.get(3).equals(keyCode)) {
                players.get(1).rightKey = false;
            }

        } else if (keyStrokesJ3.contains(keyCode)) {
            if (keyStrokesJ3.get(0).equals(keyCode)) {
                players.get(2).upKey = false;
            } else if (keyStrokesJ3.get(1).equals(keyCode)) {
                players.get(2).downKey = false;
            } else if (keyStrokesJ3.get(2).equals(keyCode)) {
                players.get(2).leftKey = false;
            } else if (keyStrokesJ3.get(3).equals(keyCode)) {
                players.get(2).rightKey = false;
            }

        } else if (keyStrokesJ4.contains(keyCode)) {
            if (keyStrokesJ4.get(0).equals(keyCode)) {
                players.get(3).upKey = false;
            } else if (keyStrokesJ4.get(1).equals(keyCode)) {
                players.get(3).downKey = false;
            } else if (keyStrokesJ4.get(2).equals(keyCode)) {
                players.get(3).leftKey = false;
            } else if (keyStrokesJ4.get(3).equals(keyCode)) {
                players.get(3).rightKey = false;
            }

        }
    }

    void addPlayer() {
        if (players.size() < 4) {
            int[] pos = gamePanel.hexagonManager.centerPositionHexagon(players.size());
            if (players.size() == 3) {
                pos = gamePanel.hexagonManager.centerPositionHexagon(players.size()+1);
            }
            new Player(gamePanel, pos[0] - 32, pos[1] - 32);
        }

    }

    public int totalPlayers() {
        return players.size();
    }

}
