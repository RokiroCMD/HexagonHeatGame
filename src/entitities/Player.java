package entitities;

import frames.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import managers.PlayerManager;
import utils.PlayerState;
import utils.SpriteSheet;
import utils.Sprites;

public class Player {

    int x;
    int y;
    Rectangle hitbox;
    SpriteSheet sprite;
    int width;
    int height;
    int numPlayer;
    public boolean upKey = false;
    public boolean downKey = false;
    public boolean leftKey = false;
    public boolean rightKey = false;
    float speed = 3.5f;
    public boolean facingDown = false;
    public boolean facingUp = false;
    public boolean facingLeft = false;
    public boolean facingRight = false;
    public boolean standing = true;
    public boolean floatAnimation = false;
    
    List<BufferedImage> UP_PLAYER_FRAME;
    List<BufferedImage> DOWN_PLAYER_FRAME;
    List<BufferedImage> LEFT_PLAYER_FRAME;
    List<BufferedImage> RIGHT_PLAYER_FRAME;
    List<BufferedImage> DEAD_PLAYER_FRAME;
    
    
    GamePanel gp;
    public PlayerState playerState = PlayerState.ALIVE;

    public Player(GamePanel gp, int x, int y) {
        this.gp = gp;
        this.x = x;
        this.y = y;
        width = 64;
        height = 64;
        numPlayer = PlayerManager.players.size() + 1;
        hitbox = new Rectangle(x, y, width, height);
        DOWN_PLAYER_FRAME = Sprites.MARIO_DOWN;
        switch (numPlayer) {
            case 1:
                UP_PLAYER_FRAME = Sprites.MARIO_UP;
                DOWN_PLAYER_FRAME = Sprites.MARIO_DOWN;
                LEFT_PLAYER_FRAME = Sprites.MARIO_LEFT;
                RIGHT_PLAYER_FRAME = Sprites.MARIO_RIGHT;
                DEAD_PLAYER_FRAME = Sprites.MARIO_DEAD;
                break;
            case 2:
                UP_PLAYER_FRAME = Sprites.PEACH_UP;
                DOWN_PLAYER_FRAME = Sprites.PEACH_DOWN;
                LEFT_PLAYER_FRAME = Sprites.PEACH_LEFT;
                RIGHT_PLAYER_FRAME = Sprites.PEACH_RIGHT;
                DEAD_PLAYER_FRAME = Sprites.PEACH_DEAD;
                break;
            case 3:
                UP_PLAYER_FRAME = Sprites.MALLOW_UP;
                DOWN_PLAYER_FRAME = Sprites.MALLOW_DOWN;
                LEFT_PLAYER_FRAME = Sprites.MALLOW_LEFT;
                RIGHT_PLAYER_FRAME = Sprites.MALLOW_RIGHT;
                DEAD_PLAYER_FRAME = Sprites.MALLOW_DEAD;
                break;
            case 4:
                UP_PLAYER_FRAME = Sprites.GENO_UP;
                DOWN_PLAYER_FRAME = Sprites.GENO_DOWN;
                LEFT_PLAYER_FRAME = Sprites.GENO_LEFT;
                RIGHT_PLAYER_FRAME = Sprites.GENO_RIGHT;
                DEAD_PLAYER_FRAME = Sprites.GENO_DEAD;
                break;

        }
        sprite = new SpriteSheet(10, DOWN_PLAYER_FRAME, x, y, 2);
        PlayerManager.players.add(this);
    }

    public void draw(Graphics2D g2) {

    }

    public void update() {

        switch (playerState) {
            case ALIVE:
                if (!upKey & !rightKey & !downKey & !leftKey) {
                    if (facingDown || facingUp || facingLeft || facingRight) {
                        if (!standing) {
                            facingRight = false;
                            facingLeft = false;
                            facingUp = false;
                            facingDown = false;
                            standing = true;
                            sprite.index = 0;
                            sprite.frames = DOWN_PLAYER_FRAME;
                        }

                    }
                    Rectangle validationHitbox = new Rectangle(x, (int) (y - speed), width, height);
                    if (!gp.hexagonManager.isInsideHexagon(validationHitbox)) {
                        playerState = PlayerState.FALLING;
                        break;
                    }
                }

                if (rightKey && !leftKey) {

                    Rectangle validationHitbox = new Rectangle((int) (x + speed), y, width, height);
                    if (!gp.hexagonManager.isInsideHexagon(validationHitbox)) {
                        playerState = PlayerState.FALLING;
                        break;
                    }

                    x += speed;
                    if (!facingRight) {
                        facingRight = true;
                        facingLeft = false;
                        facingUp = false;
                        facingDown = false;
                        standing = false;
                        sprite.index = 0;
                        sprite.frames = RIGHT_PLAYER_FRAME;
                    }
                }
                if (leftKey && !rightKey) {

                    Rectangle validationHitbox = new Rectangle((int) (x - speed), y, width, height);
                    if (!gp.hexagonManager.isInsideHexagon(validationHitbox)) {
                        playerState = PlayerState.FALLING;
                        break;
                    }

                    x -= speed;
                    if (!facingLeft) {
                        facingLeft = true;
                        facingRight = false;
                        facingUp = false;
                        facingDown = false;
                        standing = false;
                        sprite.index = 0;
                        sprite.frames = LEFT_PLAYER_FRAME;
                    }
                }
                if (upKey && !downKey) {

                    Rectangle validationHitbox = new Rectangle(x, (int) (y - speed), width, height);
                    if (!gp.hexagonManager.isInsideHexagon(validationHitbox)) {
                        playerState = PlayerState.FALLING;
                        break;
                    }

                    y -= speed;
                    if (!facingUp) {
                        if (!(rightKey || leftKey)) {
                            facingUp = true;
                            facingRight = false;
                            facingLeft = false;
                            facingDown = false;
                            standing = false;
                            sprite.index = 0;
                            sprite.frames = UP_PLAYER_FRAME;
                        }
                    }
                }

                if (downKey && !upKey) {

                    Rectangle validationHitbox = new Rectangle(x, (int) (y + speed), width, height);
                    if (!gp.hexagonManager.isInsideHexagon(validationHitbox)) {
                        playerState = PlayerState.FALLING;
                        break;
                    }

                    y += speed;
                    if (!facingDown) {
                        if (!(rightKey || leftKey)) {
                            facingDown = true;
                            facingRight = false;
                            facingLeft = false;
                            facingUp = false;
                            standing = false;
                            sprite.index = 0;
                            sprite.frames = DOWN_PLAYER_FRAME;
                        }
                    }
                }

                sprite.x = x;
                sprite.y = y;
                hitbox.x = x;
                hitbox.y = y;
                break;
            case FALLING:
                if (!floatAnimation) {
                    facingDown = false;
                    facingRight = false;
                    facingLeft = false;
                    facingUp = false;
                    standing = false;
                    sprite.index = 0;
                    sprite.frames = DEAD_PLAYER_FRAME;
                    sprite.fps = 5;
                    sprite.layer = 1;

                    floatAnimation = true;
                }
                
                
                sprite.x = x;
                sprite.y += 1;
                hitbox.x = x;
                hitbox.y = y;
                if (sprite.x > gp.frmGame.screenWidth) {
                   playerState = PlayerState.DEAD;
                }
                break;
            case DEAD:
                
                break;
        }

    }

}
