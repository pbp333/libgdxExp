package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MatrixGame.WIDTH;
		config.height = MatrixGame.HEIGHT;
		config.title = MatrixGame.TITLE;
		new LwjglApplication(new MatrixGame(), config);
	}
}
