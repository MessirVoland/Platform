package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Анимация
 * Created by Asup.D on 12.12.2016.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private Array<Sprite> frames2;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private float scale;
    TextureRegion textureRegion;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {


        scale=-0.5f;
        frames = new Array<TextureRegion>();
        frames2 = new Array<Sprite>();
        int frameWidth=region.getRegionWidth()/frameCount;
        //int frameWidth=
        for (int i=0;i<frameCount;i++){
            frames.add(new TextureRegion(region,i*frameWidth,0,frameWidth,region.getRegionHeight()));
            frames2.add(new Sprite(region,i*frameWidth,0,frameWidth,region.getRegionHeight()));
        }
        for (int i=0;i<frameCount;i++){
            frames2.get(i).scale(scale);
            //frames2.get(i).setBounds(frames2.get(i).);
            //frames2.get(i).setBounds(frames2.get(i).getWidth()-frames2.get(i).getWidth()/2,0,frames2.get(i).getWidth(),frames2.get(i).getHeight());
            //frames2.add(new Sprite(region,i*frameWidth,0,frameWidth,region.getRegionHeight()));
            //frames2.get(i).getBoundingRectangle().set(frames2.get(i).getWidth()-frames2.get(i).getWidth()/2,0,frames2.get(i).getWidth(),frames2.get(i).getHeight());
        }
        this.frameCount=frameCount;
        maxFrameTime=cycleTime / frameCount;
        frame =0;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public void setCurrentFrameTime(float currentFrameTime) {

        this.currentFrameTime = currentFrameTime;
    }

    public void update(float dt){

        currentFrameTime+=dt;
        if (currentFrameTime>maxFrameTime){
            frame++;
            currentFrameTime=0;
        }
        if (frame>=frameCount){
            frame =0;
        }
    }

    public void flip_tex(){
        for (int i=0;i<frameCount;i++){
            frames2.get(i).flip(true,false);
            frames.get(i).flip(true,false);
        }
    }
    public int get_WIDTH(){
        return frames2.get(frame).getRegionWidth();
    }
    public void next_frame(){
        frame++;
        if (frame>=frameCount){
            frame =0;
        }
    }
    public void setPosition(Vector3 pos){
        //frames2.get(frame).setPosition(pos.x,pos.y);
        frames2.get(frame).setPosition(pos.x,pos.y);
    }
    public TextureRegion getFrames() {
        return frames.get(frame);
    }
    public Sprite getFramesS() {
        return frames2.get(frame);
    }
    public void dispose(){
    frames.clear();
    frames2.clear();
    }
}
