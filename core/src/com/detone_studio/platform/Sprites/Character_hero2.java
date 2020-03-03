package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character_hero2 extends Actor {
    TextureRegion region;
    private Animation animation_idle,animation_jump,animation_walk;
    public Character_hero2(){
        animation_idle = new Animation(new TextureRegion(new Texture("atlas/idle_char_num.png")),5,0.8f);

        //region = new TextureRegion(new Texture("sprites/static_1.png"));
        //System.out.println(region.getRegionX());
        //System.out.println(region.getRegionY());
        //System.out.println(region.getRegionWidth());
        //System.out.println(region.getRegionHeight());
        setBounds(0,0,
                animation_idle.get_WIDTH(), animation_idle.get_HEIGHT());

    }

    /*
    @Override
    public void atc(float deltaTime){
        super.act(deltaTime);
    }*/

    @Override
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(animation_idle.getFrames(), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        animation_idle.update(delta);
    }
}
