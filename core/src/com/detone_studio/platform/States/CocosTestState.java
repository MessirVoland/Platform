package com.detone_studio.platform.States;


import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.detone_studio.platform.GameStateManager;
import com.detone_studio.platform.Sprites.Character_hero2;

import java.awt.Image;

public class CocosTestState extends State {

    private Stage stage;
    private Character_hero2 character_hero2;
    public CocosTestState(GameStateManager gsm) {
        super(gsm);
        stage = new Stage(new ScreenViewport(new OrthographicCamera(480,600)));
        character_hero2 = new Character_hero2();

        character_hero2.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("CLICKED");
                return false;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("REMOVED");
            }
        });
        MoveToAction action = new MoveToAction();
        //action.setPosition(100,100);
        //action.setDuration(5);
        //character_hero2.addAction(action);
        stage.addActor(character_hero2);

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
