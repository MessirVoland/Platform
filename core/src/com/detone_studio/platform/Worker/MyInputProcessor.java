package com.detone_studio.platform.Worker;

import com.badlogic.gdx.InputProcessor;
import com.detone_studio.platform.States.TestState;

public class MyInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        //TestState.man_y-=5;
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("Key pressed :"+keycode);
        switch (keycode){
            case 19:
                //up
                TestState.man_y+=5;
                break;
            case 20:
                //up
                TestState.man_y-=5;
                break;
            case 21:
                //up
                TestState.man_x-=5;
                break;
            case 22:
                TestState.man_x+=5;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
