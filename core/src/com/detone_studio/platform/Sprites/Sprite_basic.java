package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;

public abstract class Sprite_basic {
    public abstract void update(float dt);
    public abstract void draw(Batch sb);
    public abstract void set_position(int x,int y);
    public abstract Vector3 get_position();
    public abstract void dispose();
}
