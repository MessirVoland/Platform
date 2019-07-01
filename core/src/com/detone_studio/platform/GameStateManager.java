package com.detone_studio.platform;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.detone_studio.platform.States.State;

import java.util.Stack;



/**
 * Created by Voland on 04.08.2017.
 */

public class GameStateManager {

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}