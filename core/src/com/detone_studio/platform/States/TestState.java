package com.detone_studio.platform.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.detone_studio.platform.GameStateManager;
import com.detone_studio.platform.Sprites.Animation;
import com.detone_studio.platform.Worker.MyInputProcessor;

public class TestState extends State {
    public static Sprite img;
    InputProcessor inputProcessor;
    public static int man_x,man_y;
    public static Animation animation;
    private Texture ima;
    public static float animtime;

    public TestState(GameStateManager gsm) {
        super(gsm);
        ima = new Texture("atlas/idle_char.png");
        animtime= 0.8f;
        animation = new Animation(new Sprite(ima),5,animtime);
        img = new Sprite(new Texture("sprites/static_1.png"));
        man_x=10;
        man_y=10;
        img.setPosition(man_x,man_y);
        inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isTouched()) {
            man_x+=5;
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        animation.update(dt);
        img.setPosition(man_x,man_y);
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(camera.combined);
        sb.begin();
        animation.getFrames().getTexture();
        //img.draw(sb);
        sb.draw(animation.getFrames(),man_x,man_y);
        //animation.getFrames().getTexture().;
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
