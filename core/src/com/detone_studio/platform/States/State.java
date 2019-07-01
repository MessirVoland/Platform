package com.detone_studio.platform.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.detone_studio.platform.GameStateManager;


/**
 * Created by Voland on 04.08.2017.
 */

public abstract class State {

    public static OrthographicCamera camera;
    protected Vector3 mouse;
    protected static GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
    //public abstract void pause();
}
