package com.detone_studio.platform.Worker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.detone_studio.platform.States.TestState;

import static com.detone_studio.platform.Main.HEIGHT;
import static com.detone_studio.platform.Main.WIDTH;
import static com.detone_studio.platform.States.TestState.animtime;
import static com.detone_studio.platform.States.TestState.bnt_arrow;
import static com.detone_studio.platform.States.TestState.bnt_arrow_l;
import static com.detone_studio.platform.States.TestState.bnt_arrow_r;
import static com.detone_studio.platform.States.TestState.character_hero;

public class MyInputProcessor implements InputProcessor {


    Vector3 touchPos = new Vector3();
    @Override
    public boolean keyDown(int keycode) {
        //TestState.man_y-=5;
        switch (keycode) {
            case 19:
                character_hero.jump();
                break;
            case 22:
                character_hero.go_right();
                break;
            case 21:
                 character_hero.go_left();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //System.out.println("Key pressed :"+keycode);
        switch (keycode){
            case 144:
                //animtime+=0.05f;
                //animation.setCurrentFrameTime(0.7f);
                //System.out.println("Anim TIME :"+animtime);
                break;
            case 56:
                //animtime-=0.05f;
                //animation.setCurrentFrameTime(0.7f);
                //System.out.println("Anim TIME :"+animtime);
                break;
            case 20:
                //up
                //TestState.man_y+=5;
                break;
            case 19:
                //up
                character_hero.jump_over();
                //TestState.man_y-=5;
                break;
            case 21:
                //up
                character_hero.go_left_over();
                //TestState.man_x-=5;
                break;
            case 22:
                character_hero.go_right_over();

                //TestState.man_x+=5;
                break;
        }


        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, screenY, 0);
        TestState.static_camera.unproject(touchPos);
        screenX= (int) touchPos.x;
        screenY= (int) touchPos.y;


        System.out.println("TOUCHED "+screenX+" "+screenY);
        if (bnt_arrow.check_click(screenX,screenY)){
            character_hero.jump();
        };
        if (bnt_arrow_l.check_click(screenX,screenY)){
            character_hero.go_left();
        };
        if (bnt_arrow_r.check_click(screenX,screenY)){
            character_hero.go_right();
        };
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchPos.set(screenX, screenY, 0);
        TestState.camera.unproject(touchPos);
        screenX= (int) touchPos.x;
        screenY= (int) touchPos.y;
        if (bnt_arrow.check_click(screenX,screenY)){
            character_hero.jump_over();
        };
        if (bnt_arrow_l.check_click(screenX,screenY)){
           character_hero.go_left_over();
        };
        if (bnt_arrow_r.check_click(screenX,screenY)){
            character_hero.go_right_over();
        };

        /*if (character_hero.ismoving()){
            character_hero.go_left_over();
             character_hero.go_right_over();
        }*/

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
