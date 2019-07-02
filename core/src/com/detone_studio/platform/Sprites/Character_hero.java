package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Character_hero extends Sprite_basic {
    private int local_x,local_y;
    private Sprite sprite;
    private Animation animation;
    private Vector3 position;
    private Vector3 velosity;
    private int speed_grav;
    private boolean right,left,look_right,isJumping;


    public Character_hero(int x, int y) {
        //super(x, y);

        //Позиция и вектор ускорения
        look_right=true;
        isJumping=false;
        right=false;
        left=false;
        position = new Vector3(x, y, 0);

        speed_grav=-100;
        velosity = new Vector3(0, speed_grav, 0);

        //local_x=x;
        //local_y=y;
        animation = new Animation(new Sprite(new Texture("atlas/idle_char.png")),5,0.8f);
        //sprite = new Sprite(new Texture(""));

    }

    public void jump(){
        if (!isJumping) {
            isJumping=true;
            velosity.set(velosity.x, 500, 0);
        }
    }
    public void jump_over(){
        if (velosity.y>0) {
            velosity.set(velosity.x, 0, 0);
        }
    }
    public void go_right(){
        right=true;
        if (!look_right){
            look_right=true;
            animation.flip_tex();
        }
    }
    public void go_right_over(){
        right=false;
    }

    public void go_left(){
        if (look_right) {
            look_right=false;
            animation.flip_tex();
        }
        left=true;
    }
    public void go_left_over(){
        left=false;
    }

    public int GetX(){
        return Math.round(position.x);
    }
    public int GetY(){
        return Math.round(position.y);
    }
    public int GetWidth(){
        return animation.getFrames().getRegionWidth();
    }
    public int GetHeight(){
        return animation.getFrames().getRegionHeight();
    }

    @Override
    public void update(float dt) {
        animation.update(dt);
        velosity.scl(dt);
        position.add(velosity.x, velosity.y, 0);
        velosity.scl(1 / dt);

        if (position.y<64){
            position.y=64;
            isJumping=false;
        }

        if (velosity.y>-500){
            velosity.y-=25;
        }

        if (right){
            velosity.x+=5;
            if (velosity.x<0){
                velosity.x+=5;
            }
        }
        else if (left){
            velosity.x-=5;
            if (velosity.x>0){
                velosity.x-=5;
            }
        }
        else
            {
                if (velosity.x>5){
                    velosity.x-=5;
                }else if (velosity.x<-5){
                    velosity.x+=5;
                }else{
                    velosity.x=0;
                }
        }
        if (velosity.x>200){
            velosity.x=200;
        }
        if (velosity.x<-200){
            velosity.x=-200;
        }




    }

    @Override
    public void draw(Batch sb) {

        sb.draw(animation.getFrames(),position.x,position.y);
    }


    @Override
    public void dispose() {
        animation.dispose();
    }
}
