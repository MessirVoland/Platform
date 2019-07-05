package com.detone_studio.platform.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.detone_studio.platform.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title="Платформер";
		config.width=Main.WIDTH;
		config.height=Main.HEIGHT;
		//config.vSyncEnabled=false;
		//config.foregroundFPS = 0;
		//config.forceExit = true;
		//new LwjglApplication(new MyGdxGame(null,null,null), config)

		new LwjglApplication(new Main(), config);
	}
}
