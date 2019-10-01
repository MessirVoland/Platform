package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Fairy extends Sprite_basic {
    private Sprite sprite_fairy;
    private Vector3 position,velocity;
    private boolean switcher_vert=true,switcher_horz=true;

    public Fairy(int x,int y){
        position= new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        sprite_fairy=new Sprite(new Texture("sprites/Fairy.png"));
        sprite_fairy.setCenter(position.x,position.y);
    }
    @Override
    public void update(float dt) {

        //случайно-неслучайное движение фейки
        if (switcher_vert){
            if (velocity.y<40){
                velocity.y++;
            }else
            {
                switcher_vert=false;
            }
        }
        else
        {
            if (velocity.y>-40){
                velocity.y--;
            }else
            {
                switcher_vert=true;
            }
        }

        if (switcher_horz){
            if (velocity.x<20.0f){
                velocity.x+=0.5f;
            }else
            {
                switcher_horz=false;
            }
        }else
        {
            if (velocity.x>-20.0f){
                velocity.x-=0.5f;
            }else
            {
                switcher_horz=true;
            }
        }

        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1 / dt);

        sprite_fairy.setCenter(position.x,position.y);
    }

    @Override
    public void draw(Batch sb) {
        sprite_fairy.draw(sb);
    }

    @Override
    public void set_position(float x, float y) {
        position.set(x,y,0);
    }

    @Override
    public Vector3 get_position() {
        return position;
    }

    @Override
    public void dispose() {

    }

    public Rectangle getBoundRectangle(){
        return sprite_fairy.getBoundingRectangle();
    }
}
