package com.detone_studio.platform.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.detone_studio.platform.GameStateManager;

import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.detone_studio.platform.States.TestState.ON_LEVEL;
import static com.detone_studio.platform.States.TestState.character_hero;
import static com.detone_studio.platform.States.TestState.savedPosition;


public class house extends State {
    private Sprite walls,door;
    private boolean entered;
    public house(GameStateManager gsm) {
        super(gsm);
        entered=true;
        System.out.println("Entered house");
        walls = new Sprite(new Texture("sprites/Bloks.png"));
        door = new Sprite(new Texture("sprites/Home.png"));
    }

    @Override
    protected void handleInput() {
         //if(Gdx.input.isTouched()) {
             if(Gdx.input.isKeyJustPressed(DOWN)) {
                 System.out.println("Exit house");
                 ON_LEVEL=true;
                 character_hero.setPosition(savedPosition.x,savedPosition.y);
                 gsm.pop();
             }else
             {
                 //System.out.println("wrong button");
             }
            ;

        //}
    }

    @Override
    public void update(float dt) {
        handleInput();
        character_hero.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        walls.draw(sb);
        character_hero.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
