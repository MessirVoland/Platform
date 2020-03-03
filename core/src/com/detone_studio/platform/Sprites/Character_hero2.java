package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character_hero2 extends Actor {
    TextureRegion region;
    public Character_hero2(){
        region = new TextureRegion(new Texture("sprites/static_1.png"));
        System.out.println(region.getRegionX());
        System.out.println(region.getRegionY());
        System.out.println(region.getRegionWidth());
        System.out.println(region.getRegionHeight());
        setBounds(region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
