package com.detone_studio.platform;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.detone_studio.platform.States.Stage0;
import com.detone_studio.platform.States.TestState;

public class Main implements ApplicationListener {

	private GameStateManager gsm;
	private SpriteBatch batch;
	OrthographicCamera cam;
	public static final int WIDTH = 1280; //480
	public static final int HEIGHT = 720; // 800
	//SpriteBatch batch;

	
	@Override
	public void create () {

		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		gsm = new GameStateManager();
		//Gdx.gl20.glEnable(GL30.GL_BLEND);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		gsm.push(new Stage0(gsm));
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
