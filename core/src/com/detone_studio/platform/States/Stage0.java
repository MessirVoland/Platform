package com.detone_studio.platform.States;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.detone_studio.platform.GameStateManager;
import com.detone_studio.platform.Sprites.Character_hero;
import com.detone_studio.platform.Sprites.Character_hero_new;

public class Stage0 extends State {
    Character_hero_new character_hero;

    public Stage0(GameStateManager gsm) {
        super(gsm);
    character_hero= new Character_hero_new(10,10);


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        character_hero.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        character_hero.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
