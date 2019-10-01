package com.detone_studio.platform.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.detone_studio.platform.GameStateManager;
import com.detone_studio.platform.Sprites.Bnt_arrow;

import static com.badlogic.gdx.Input.Keys.DOWN;
import static com.detone_studio.platform.States.TestState.*;


public class house extends State {
    private Sprite walls,door;
    private boolean entered;
    public static Bnt_arrow bnt_arrow_back;

    private OrthographicCamera house_camera;

    public house(GameStateManager gsm) {
        super(gsm);
        entered=true;

        house_camera= new OrthographicCamera();
        house_camera.setToOrtho(false,1280 , 720);

        bnt_arrow_back = new Bnt_arrow(750,0,-270);
        System.out.println("Entered house");
        walls = new Sprite(new Texture("sprites/Bloks.png"));
        door = new Sprite(new Texture("sprites/Home.png"));
    }

    @Override
    protected void handleInput() {
        //выход из дома только если нажал вниз на двери.
        //скорей всего нужен фикс что бы выходил по кнопке вверх как везде.
             if((Gdx.input.isKeyJustPressed(DOWN))|(Gdx.input.justTouched()&(bnt_arrow_back.check_click(Gdx.input.getX(),720-Gdx.input.getY())))) {
                 if (character_hero.getBoundRectangle().overlaps(door.getBoundingRectangle())) {
                     System.out.println("Exit house");
                     ON_LEVEL = true;
                     character_hero.set_position(savedPosition.x, savedPosition.y);
                     gsm.pop();
                 }
             }else
             {
                       //System.out.println("wrong button");
             }

    }

    @Override
    public void update(float dt) {
        handleInput();
        character_hero.update(dt);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        house_camera.update();//неверен что именно тут нужен апдейт камеры
        sb.setProjectionMatrix(house_camera.combined);
        walls.draw(sb);
        door.draw(sb);
        character_hero.draw(sb);
        bnt_arrow_back.draw(sb);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
