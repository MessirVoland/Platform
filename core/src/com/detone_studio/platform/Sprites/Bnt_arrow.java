package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Bnt_arrow extends Sprite_basic {
    //private Vector3 position;
    private Sprite sprite;
    public Bnt_arrow(int position_x,int position_y,float rotation){
        //position = new Vector3(position_x,position_y,0);
        sprite = new Sprite(new Texture("non_project_tiles/arrow.png"));
        sprite.setPosition(position_x,position_y);
        sprite.rotate(rotation);
        sprite.setAlpha(0.5f);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw(Batch sb) {
        sprite.draw(sb);
    }

    @Override
    public void dispose() {

    }
    public boolean check_click(int x, int y){

        //System.out.println(" x "+x+" y "+y);
       // System.out.println(" sx "+sprite.getX()+" sy "+sprite.getY());
        return sprite.getBoundingRectangle().contains(x,y);
    }
}
