package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25; //velocidad de animacion del personaje
    private int playerAction = IDLE; //animacion por defecto en estado inmovil
    private boolean moving = false, jumping = false; //regreso a estado IDLE si no hace ninguna accion
    private boolean  left, up, right, down;
    private float playerSpeed = 2.0f; //velocidad de movimiento al andar
    private int[][] lvlData;
    private float xDrawOffset = 8* Game.SCALE; //ayuda a sincronizar el hitbox con el personaje
    private float yDrawOffset = 8 * Game.SCALE; // ayuda a sincronizar el hitbox con el personajel

    //tamaño y ubicacion del personaje dentro del mapa
    public Player(float x, float y, int width, int height) {
        super(x,y,width,height);
        loadAnimations();
        initHitBox(x,y,45*Game.SCALE,45*Game.SCALE);
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g) {

        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
        drawHitbox(g);

    }

    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                jumping = false;
            }
        }
    }

        private void setAnimation () {

        int startAni = playerAction;

        if (moving)
                playerAction = RUNNING;
            else
                playerAction = IDLE;
            if (jumping)
                playerAction = JUMP;
            if(startAni != playerAction)
                resetAniTick();
        }

        private void resetAniTick(){
        aniTick=0;
        aniIndex=0;
        }

    private  void updatePos(){

        moving = false;
        if(!left && !right && !up && !down)
            return;

        float xSpeed=0, ySpeed=0;

        if (left && !right)
            xSpeed = -playerSpeed;
            else if(right && !left)
            xSpeed=playerSpeed;

        if (up && !down)
            ySpeed=-playerSpeed;
         else if(down && !up)
            ySpeed = playerSpeed;
/*         if(CanMoveHere(x+xSpeed, y+ySpeed, width, height, lvlData)){
             this.x += xSpeed;
             this.y += ySpeed;
             moving = true;
         }*/

        if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)){
            hitbox.x += xSpeed;
            hitbox.y += ySpeed;
            moving = true;
        }


    }

    private void loadAnimations() {

            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[3][4]; //el numero de imagenes que hay en el archivo sprite, 2 corresponden a X y 3 a Y
            for (int j = 0; j < animations.length; j++)
                for (int i = 0; i < animations[j].length; i++)
                    animations[j][i] = img.getSubimage(i *205, j *180, 205, 180); //dimensionens del personaje y posicion s y W, Y y H deben coincidir

        }

        public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
        }

        public void resetDirBooleans(){
        left=false;
        right=false;
        up=false;
        down=false;
        }
        public void setJumping(boolean jumping){
        this.jumping = jumping;
        }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
