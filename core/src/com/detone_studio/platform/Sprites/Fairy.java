package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
            if (velocity.y<10){
                velocity.y++;
            }else
            {
                switcher_vert=true;
            }
        }
        else
        {
            if (velocity.y>-10){
                velocity.y--;
            }else
            {
                switcher_vert=false;
            }
        }

        if (switcher_horz){
            if (velocity.x<10){
                velocity.x++;
            }else
            {
                switcher_horz=true;
            }
        }else
        {
            if (velocity.x>-10){
                velocity.x--;
            }else
            {
                switcher_horz=false;
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
    public void set_position(int x, int y) {
        position.set(x,y,0);
    }

    @Override
    public Vector3 get_position() {
        return position;
    }

    @Override
    public void dispose() {

    }
}
