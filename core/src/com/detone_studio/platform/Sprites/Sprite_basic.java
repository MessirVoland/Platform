package com.detone_studio.platform.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Sprite_basic {
    public abstract void update(float dt);
    public abstract void draw(Batch sb);
    public abstract void dispose();
}
