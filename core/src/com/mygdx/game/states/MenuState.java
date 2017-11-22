package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MatrixGame;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager gsm){
        super(gsm);
        background = new Texture("matrixwallpaper.png");
        playButton = new Texture("Icon_Bed.png");
    }
    @Override
    public void handleInput() {

        if(Gdx.input.justTouched()){

            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        sb.draw(background, 0, 0, MatrixGame.WIDTH, MatrixGame.HEIGHT);
        sb.draw(playButton, MatrixGame.WIDTH / 2 - playButton.getWidth() / 2, MatrixGame.HEIGHT / 2 - playButton.getHeight() / 2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
