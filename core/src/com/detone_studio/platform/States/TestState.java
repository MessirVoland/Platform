package com.detone_studio.platform.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.detone_studio.platform.GameStateManager;
import com.detone_studio.platform.Sprites.Animation;
import com.detone_studio.platform.Sprites.Character_hero;
import com.detone_studio.platform.Worker.MyInputProcessor;

public class TestState extends State {
    public static Sprite img,grass,grass2,grass3;
    InputProcessor inputProcessor;
    public static int man_x,man_y;
    private BitmapFont FontRed1;
    public static Character_hero character_hero;


    private Texture ima;

    public static float animtime;

    public TestState(GameStateManager gsm) {
        super(gsm);
        FontRed1 = new BitmapFont();
        FontRed1.setColor(Color.RED); //Красный

        grass = new Sprite(new Texture("sprites/Platform1.png"));
        grass.setPosition(0,0);
        grass2 = new Sprite(new Texture("sprites/Platform1.png"));
        grass2.setPosition(64,0);
        grass3 = new Sprite(new Texture("sprites/Platform1.png"));
        grass3.setPosition(128,0);



        img = new Sprite(new Texture("sprites/static_1.png"));
        man_x=30;
        man_y=64;
        character_hero = new Character_hero(man_x,man_y);
        //img.setPosition(man_x,man_y);
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
        //img.setPosition(man_x,man_y);
        character_hero.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(camera.combined);
        sb.begin();
        grass.draw(sb);
        grass2.draw(sb);
        grass3.draw(sb);
        character_hero.draw(sb);


        int fps = Gdx.graphics.getFramesPerSecond();
        if (fps >= 45) {
            // 45 or more FPS show up in green
            FontRed1.setColor(0, 1, 0, 1);
        } else if (fps >= 30) {
            // 30 or more FPS show up in yellow
            FontRed1.setColor(1, 1, 0, 1);
        } else {
            // less than 30 FPS show up in red
            FontRed1.setColor(1, 0, 0, 1);
        }
        FontRed1.draw(sb, " FPS : "+  fps, 10, 470);


        //animation.getFrames().getTexture();
        //img.draw(sb);
        //animation.getFrames().getTexture().;
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
