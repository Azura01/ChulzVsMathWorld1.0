package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

    protected float x,y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;
    public Entity(float x, float y, int width, int height){
        this.x = 120;
        this.y = 120;
        this.width=120; //tamaño del hitbox
        this.height=120; //tamaño del hitbox
    }

    protected void drawHitbox(Graphics g){
        //para desbugear la coalisionn del hitbox
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.width);
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x,y,width,height);
    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }

}
