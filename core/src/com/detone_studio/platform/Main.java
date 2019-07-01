package com.detone_studio.platform;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.detone_studio.platform.States.TestState;

public class Main implements ApplicationListener {

	private GameStateManager gsm;
	private SpriteBatch batch;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800; // 800
	//SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		//Gdx.gl20.glEnable(GL30.GL_BLEND);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		gsm.push(new TestState(gsm));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (gsm!=null) {
			gsm.update(Gdx.graphics.getDeltaTime());
			gsm.render(batch);
		}

		Gdx.gl.glFlush();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
