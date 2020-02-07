package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.detone_studio.platform.Worker.CharInputProcessor;

public class Character_hero_new extends Sprite_basic {

    private Animation animation_idle;
    private Vector3 position;
    private Vector3 velosity;
    private int speed_grav;
    private boolean right,left,look_right,isJumping,isFallOut;

    public Character_hero_new(int x, int y){
        CharInputProcessor charInputProcessor = new CharInputProcessor();
        charInputProcessor.setHero(this);
        Gdx.input.setInputProcessor(charInputProcessor);
        animation_idle = new Animation(new TextureRegion(new Texture("atlas/idle_char_num.png")),5,0.8f);

        position = new Vector3(x, y, 0);

        speed_grav=-20;
        velosity = new Vector3(0, speed_grav, 0);
    }

    public void jump(){
        if (!isJumping) {
           // up=true;
            isJumping=true;
            velosity.set(velosity.x, 800, 0);
        }
    }
    public void jump_over(){
        if (velosity.y>0) {
           // up=false;
            velosity.set(velosity.x, 100, 0);
        }
    }

    public void go_right(){

    }
    public void go_right_over(){

    }

    public void go_left(){

    }
    public void go_left_over(){

    }

    @Override
    public void update(float dt) {
        animation_idle.update(dt);
        velosity.scl(dt);
        position.add(velosity.x, velosity.y, 0);
        velosity.scl(1 / dt);

        animation_idle.setPosition(position);
        if (isJumping) {
            if (velosity.y > -600) {
                velosity.y += speed_grav;
            }
        }else {
            velosity.y=0;
        }
        if (position.y<=10){
            position.y=10;

            if (velosity.y<0) {
                isJumping = false;
            }
        }


    }


    @Override
    public void draw(Batch sb) {
        animation_idle.getFramesS().draw(sb);
    }

    @Override
    public void set_position(float x, float y) {

    }

    @Override
    public Vector3 get_position() {
        return null;
    }

    @Override
    public void dispose() {

    }
}
