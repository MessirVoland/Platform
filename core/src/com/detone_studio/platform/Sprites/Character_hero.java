package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.detone_studio.platform.States.house;

import static com.detone_studio.platform.States.TestState.ON_LEVEL;
import static com.detone_studio.platform.States.TestState.character_hero;
import static com.detone_studio.platform.States.TestState.enter_house;
import static com.detone_studio.platform.States.TestState.grass;
import static com.detone_studio.platform.States.TestState.grass2;
import static com.detone_studio.platform.States.TestState.grass3;
import static com.detone_studio.platform.States.TestState.home;
import static com.detone_studio.platform.States.TestState.shaderProgram;
import static com.detone_studio.platform.States.TestState.shockWave;

public class Character_hero extends Sprite_basic {

    private int local_x,local_y;
    private Sprite sprite;
    private Animation animation_idle,animation_jump,animation_walk;
    private Vector3 position;
    private Vector3 velosity;
    private int speed_grav;


    //private Vector2 polyline_set_down;

    private boolean right,left,look_right,isJumping,isFallOut;

    private boolean up,down,high,stand;
    private Rectangle rect_down,rect_char;

    int groud_pos,speed_move,speed_max,speed_brake;
    private double slower,haster;
    public Character_hero(int x, int y) {
        //super(x, y);

        groud_pos=64;
        //максимальная скорость ускорения
        speed_move=10;
        //максимальная скорость торможения
        speed_brake=20;
        //максимальная боковая скорость
        speed_max=300;
        //скорость торможения
        slower=0;
        //скорость ускорения
        haster=0;


        //прыжок
        up=false;
        down=false;
        high=false;

        isFallOut=false;

        stand=true;
        //Позиция и вектор ускорения
        look_right=true;
        isJumping=false;
        right=false;
        left=false;
        position = new Vector3(x, y, 0);

        speed_grav=-20;
        velosity = new Vector3(0, speed_grav, 0);

        //local_x=x;
        //local_y=y;
        new TextureRegion();
        animation_jump = new Animation(new TextureRegion(new Texture("atlas/jump.png")),4,1.0f);
        animation_idle = new Animation(new TextureRegion(new Texture("atlas/idle_char_num.png")),5,0.8f);
        animation_walk = new Animation(new TextureRegion(new Texture("atlas/walk_char2.png")),5,0.5f);
        //sprite = new Sprite(new Texture(""));

        float[] vertices={
                position.x+(animation_idle.get_WIDTH()/2),position.y,
                position.x+(animation_idle.get_WIDTH()/2),position.y-100
        };

        rect_char=new Rectangle();
        rect_char.setSize(animation_idle.get_WIDTH()/2,animation_idle.get_HEIGHT()/2);
        rect_char.setPosition(position.x+animation_idle.get_WIDTH()/4,position.y);

        rect_down= new Rectangle();
        rect_down.setSize(animation_idle.get_WIDTH()/3,1);
        rect_down.setPosition(position.x+animation_idle.get_WIDTH()/4+10,position.y-1);

        //polyline_set_down=new Vector2(position.x+(animation_idle.get_WIDTH()/2),position.y-5);
    }

    public void jump(){

        if (ON_LEVEL){
            shockWave.begin();
            //Прыжки вне дома
            if (home.getBoundingRectangle().overlaps(character_hero.getBoundRectangle())) {
                System.out.println("Home overlaps jump");
                enter_house();
            }
            else if (!isJumping) {
                // up=true;
                isJumping=true;
                velosity.set(velosity.x, 800, 0);
            }
        }
        //Прыжки в доме
        else if (!isJumping) {
           // up=true;
            isJumping=true;
            velosity.set(velosity.x, 800, 0);
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
            animation_walk.flip_tex();
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
            animation_walk.flip_tex();
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


        rect_char.setPosition(position.x+animation_idle.get_WIDTH()/4,position.y);
        rect_down.setPosition(position.x+animation_idle.get_WIDTH()/4+10,position.y-1);
        animation_idle.update(dt);
        animation_walk.update(dt);

        animation_walk.update_frame_time(1.2f-(1.0f/300)*Math.abs(velosity.x));
        velosity.scl(dt);
        position.add(velosity.x, velosity.y, 0);
        velosity.scl(1 / dt);

        if (position.y<groud_pos){
            position.y=groud_pos;
            //System.out.println("Origin + " +animation_idle.getFramesS().getBoundingRectangle().getY());
            //System.out.println("Origin + " +animation_idle.getFramesS().getY());
            //System.out.println("Height + " +animation_idle.getFramesS().getHeight());
            if (velosity.y<0) {
                isJumping = false;
            }
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

        //slower=(velosity.x*speed_move/speed_max)-5;
        haster= -(speed_move*((Math.abs(velosity.x)-speed_max)/speed_max)-5);
        //slower= -(speed_move*((velosity.x-speed_max)/speed_max)-5);
        //−400x−10y+4000=0

        //−10x−400y+4000=0

        slower=-((speed_move*Math.abs(velosity.x)-(speed_max+100)*speed_brake)/speed_max);





        if (velosity.y>-600){
            velosity.y+=speed_grav;
        }

        if (right){
            velosity.x+=speed_move;
            if (velosity.x<0){ velosity.x+=haster;            }
        }
        else if (left){
            velosity.x-=speed_move;
            if (velosity.x>0){ velosity.x-=haster;            }
        }
        else
            {
                //System.out.println("Veloc :"+velosity.x);
                //System.out.println("slower :"+slower);
                if (velosity.x>10){
                    velosity.x-=slower;
                }else if (velosity.x<-10){
                    velosity.x+=slower;
                }else{
                    velosity.x=0;
                }
        }
        if (velosity.x>speed_max){
            velosity.x=speed_max;
        }
        if (velosity.x<-speed_max){
            velosity.x=-speed_max;
        }


        //polyline_set_down.x=position.x+(animation_idle.get_WIDTH()/2);
        //polyline_set_down.y=position.y-5;

        if(ON_LEVEL) {
            if (!check_groud()) {
                isJumping = true;
                animation_jump.setFrame(2);
            }
            ;
        }
        //animation_idle.getFramesS().setP
        animation_idle.setPosition(position);
        animation_jump.setPosition(position);
        animation_walk.setPosition(position);

        if ((velosity.x!=0)&(velosity.y!=0)){
            stand=false;
        }else
            stand=true;
    }

    @Override
    public void draw(Batch sb) {

        if (isJumping){
            shaderProgram.setUniformf("hard_light",0.5f);
            //sb.draw(animation_jump.getFramesS(),position.x-25,position.y-25);
            animation_jump.getFramesS().translateY(-(animation_jump.getFramesS().getHeight()-animation_jump.getFramesS().getOriginY())/2);
            animation_jump.getFramesS().draw(sb);
            animation_jump.getFramesS().translateY(+(animation_jump.getFramesS().getHeight()-animation_jump.getFramesS().getOriginY())/2);
        }else {
            if(stand) {
                shaderProgram.setUniformf("hard_light",1.0f);
                animation_idle.getFramesS().translateY(-(animation_idle.getFramesS().getHeight() - animation_idle.getFramesS().getOriginY()) / 2);
                animation_idle.getFramesS().draw(sb);
                animation_idle.getFramesS().translateY(+(animation_idle.getFramesS().getHeight() - animation_idle.getFramesS().getOriginY()) / 2);
            }else
            {
                shaderProgram.setUniformf("hard_light",1.5f);
                animation_walk.getFramesS().translateY(-(animation_walk.getFramesS().getHeight() - animation_walk.getFramesS().getOriginY()) / 2);
                animation_walk.getFramesS().draw(sb);
                animation_walk.getFramesS().translateY(+(animation_walk.getFramesS().getHeight() - animation_walk.getFramesS().getOriginY()) / 2);
            }

            //sb.draw(animation_idle.getFrames(), position.x, position.y);
        }
    }

    @Override
    public void set_position(int x, int y) {
        character_hero.position.set(x,y,0);
    }

    @Override
    public Vector3 get_position() {
        return null;
    }

    public Rectangle getBoundRectangle(){
        return rect_char;
    }

    @Override
    public void dispose() {
        animation_idle.dispose();
    }

    /*
    public void setPosition(float x,float y){
        character_hero.position.set(x,y,0);
    }*/

    public boolean ismoving() {
        return right|left;
    }
    private boolean check_groud(){
        if (grass.getBoundingRectangle().overlaps(rect_down)){
            //System.out.println("Height Orig + " +rect_down.y);
            groud_pos=(int)(grass.getBoundingRectangle().y+grass.getBoundingRectangle().height);
            return true;
        }else
        if (grass3.getBoundingRectangle().overlaps(rect_down)){
            groud_pos=(int)(grass3.getBoundingRectangle().y+grass3.getBoundingRectangle().height);
            return true;
        }else
        if (grass2.getBoundingRectangle().overlaps(rect_down)){
            groud_pos=(int)(grass2.getBoundingRectangle().y+grass2.getBoundingRectangle().height);
            return true;
        } else {
            if ((!isJumping) & (position.y > 5)) {
                groud_pos = 0;
                return false;
            }

            groud_pos = 0;
            return true;
        }

    }

}
