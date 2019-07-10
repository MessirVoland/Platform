package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static com.detone_studio.platform.States.TestState.grass;
import static com.detone_studio.platform.States.TestState.grass2;
import static com.detone_studio.platform.States.TestState.grass3;

public class Character_hero extends Sprite_basic {
    private int local_x,local_y;
    private Sprite sprite;
    private Animation animation_idle,animation_jump;
    private Vector3 position;
    private Vector3 velosity;
    private int speed_grav;


    private Vector2 polyline_set_down;

    private boolean right,left,look_right,isJumping;

    private boolean up,down,high;

    int groud_pos;

    public Character_hero(int x, int y) {
        //super(x, y);



        groud_pos=64;
        //прыжок
        up=false;
        down=false;
        high=false;

        //Позиция и вектор ускорения
        look_right=true;
        isJumping=false;
        right=false;
        left=false;
        position = new Vector3(x, y, 0);

        speed_grav=-10;
        velosity = new Vector3(0, speed_grav, 0);

        //local_x=x;
        //local_y=y;
        animation_jump = new Animation(new Sprite(new Texture("atlas/jump.png")),4,1.0f);
        animation_idle = new Animation(new Sprite(new Texture("atlas/idle_char.png")),5,0.8f);
        //sprite = new Sprite(new Texture(""));

        float[] vertices={
                position.x+(animation_idle.get_WIDTH()/2),position.y,
                position.x+(animation_idle.get_WIDTH()/2),position.y-100
        };

        polyline_set_down=new Vector2(position.x+(animation_idle.get_WIDTH()/2),position.y-32);
    }

    public void jump(){
        if (!isJumping) {
           // up=true;
            isJumping=true;
            velosity.set(velosity.x, 500, 0);
        }
    }
    public void jump_over(){
        if (velosity.y>0) {
           // up=false;
            velosity.set(velosity.x, 0, 0);
        }
    }


    public void go_right(){
        right=true;
        if (!look_right){
            look_right=true;
            animation_idle.flip_tex();
            animation_jump.flip_tex();
        }
    }
    public void go_right_over(){
        right=false;
    }

    public void go_left(){
        if (look_right) {
            look_right=false;
            animation_idle.flip_tex();
            animation_jump.flip_tex();
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
        return animation_idle.getFrames().getRegionWidth();
    }
    public int GetHeight(){
        return animation_idle.getFrames().getRegionHeight();
    }

    @Override
    public void update(float dt) {
        animation_idle.update(dt);
        velosity.scl(dt);
        position.add(velosity.x, velosity.y, 0);
        velosity.scl(1 / dt);

        if (position.y<groud_pos){
            position.y=groud_pos;
            isJumping=false;
        }

        if (isJumping){
            if (velosity.y>250){
                if (!up) {
                    animation_jump.setFrame(0);
                    up = true;
                    down = false;
                    high=false;
                }
            }else if(velosity.y>-250){
                if (!high) {
                    up = false;
                    high = true;
                    animation_jump.setFrame(2);
                    //animation_jump.next_frame();
                }
            }else{
                if(!down) {
                    animation_jump.setFrame(1);
                    //animation_jump.next_frame();
                    high = false;
                    down = true;
                }
            }

        }

        if (velosity.y>-500){
            velosity.y+=speed_grav;
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


        polyline_set_down.x=position.x+(animation_idle.get_WIDTH()/2);
        polyline_set_down.y=position.y-32;

        check_groud();
    }

    @Override
    public void draw(Batch sb) {

        if (isJumping){
            sb.draw(animation_jump.getFrames(),position.x-25,position.y-25);
        }else {
            sb.draw(animation_idle.getFrames(), position.x, position.y);
        }
    }


    @Override
    public void dispose() {
        animation_idle.dispose();
    }

    public boolean ismoving() {
        return right|left;
    }
    private void check_groud(){
        if (grass.getBoundingRectangle().contains(polyline_set_down)){
            groud_pos=(int)(grass.getBoundingRectangle().y+grass.getBoundingRectangle().height);
        }else
        if (grass3.getBoundingRectangle().contains(polyline_set_down)){
            groud_pos=(int)(grass3.getBoundingRectangle().y+grass3.getBoundingRectangle().height);
        }else
        if (grass2.getBoundingRectangle().contains(polyline_set_down)){
            groud_pos=(int)(grass2.getBoundingRectangle().y+grass2.getBoundingRectangle().height);
        } else
            groud_pos=0;

    }

}
