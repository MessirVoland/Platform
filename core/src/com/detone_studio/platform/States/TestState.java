package com.detone_studio.platform.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.math.Rectangle;
import com.detone_studio.platform.GameStateManager;
import com.detone_studio.platform.Sprites.Animation;
import com.detone_studio.platform.Sprites.Bnt_arrow;
import com.detone_studio.platform.Sprites.Character_hero;
import com.detone_studio.platform.Worker.MyInputProcessor;


public class TestState extends State {
    public static Sprite img,grass,grass2,grass3;
    InputProcessor inputProcessor;
    boolean isOverlaping;
    public static int man_x,man_y;
    private BitmapFont FontRed1;
    public static Character_hero character_hero;
    public static Sprite health_potion;

    public static Bnt_arrow bnt_arrow_l;
    public static Bnt_arrow bnt_arrow;
    public static Bnt_arrow bnt_arrow_r;

    public Rectangle rec_health_potion;
    public Rectangle rec_character_hero;


    private Texture back_ground;


    public static float animtime;

    public TestState(GameStateManager gsm) {
        super(gsm);

        //camera.unproject(Gdx.input.);


        bnt_arrow_l = new Bnt_arrow(0,0,0);
        bnt_arrow_r = new Bnt_arrow(130,0,180);
        bnt_arrow = new Bnt_arrow(1150,0,-90);

        FontRed1 = new BitmapFont();
        FontRed1.setColor(Color.RED); //Красный
        isOverlaping =false;
        health_potion = new Sprite(new Texture("non_project_tiles/heal_potion_2.png"));
        health_potion.setPosition(320,64);

        rec_health_potion= new Rectangle(health_potion.getX(),health_potion.getY(),health_potion.getWidth(),health_potion.getHeight());


        back_ground=new Texture("sprites/Image2.png");
        grass = new Sprite(new Texture("sprites/Platform1.2.png"));
        grass.setPosition(0,0);
        grass2 = new Sprite(new Texture("sprites/Platform1.2.png"));
        grass2.setPosition(64,0);
        grass3 = new Sprite(new Texture("sprites/Platform1.2.png"));
        grass3.setPosition(128,0);



        img = new Sprite(new Texture("sprites/static_1.png"));
        man_x=30;
        man_y=64;
        character_hero = new Character_hero(man_x,man_y);
        rec_character_hero=new Rectangle(character_hero.GetX(),character_hero.GetY(),character_hero.GetWidth(),character_hero.GetHeight());
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
        rec_character_hero.x=character_hero.GetX();
        rec_character_hero.y=character_hero.GetY();

        if (!isOverlaping) {

            isOverlaping = health_potion.getBoundingRectangle().overlaps(rec_character_hero);
            if (isOverlaping){
                System.out.println("Heal potion taken");
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(back_ground,0,0);
        sb.draw(back_ground,480,0);
        grass.draw(sb);
        grass2.draw(sb);
        grass3.draw(sb);
        if (!isOverlaping) {
            health_potion.draw(sb);
        }
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



        bnt_arrow.draw(sb);
        bnt_arrow_l.draw(sb);
        bnt_arrow_r.draw(sb);
        //animation.getFrames().getTexture();
        //img.draw(sb);
        //animation.getFrames().getTexture().;
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
