package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MatrixGame;
import com.mygdx.game.Sprites.Neo;
import com.mygdx.game.Sprites.Enemy;

import javax.swing.*;

public class PlayState extends State {

    private static final int ENEMY_SPACING = 125;
    private static final int ENEMY_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -20;
    private static final int CAMERA_OFFSET = 200;

    private Neo neo;
    private Texture bg;
    private Texture ground;

    private Array<Vector2> groundPositions;

    private Array<Enemy> enemies;

    public PlayState(GameStateManager gsm) {

        super(gsm);
        neo = new Neo(0, 300);
        cam.setToOrtho(false, MatrixGame.WIDTH / 2, MatrixGame.HEIGHT / 2);
        bg = new Texture("matrixcity.png");
        ground = new Texture("ground.png");

        groundPositions = new Array<Vector2>();

        for (int i = 0; i < MatrixGame.WIDTH; i++) {

            groundPositions.add(new Vector2(-CAMERA_OFFSET + ground.getWidth() * i, GROUND_Y_OFFSET));
        }

        enemies = new Array<Enemy>();

        for (int i = 1; i < ENEMY_COUNT; i++) {

            enemies.add(new Enemy(i * (ENEMY_SPACING * 2 + Enemy.ENEMY_WIDTH)));
        }
    }

    @Override
    public void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            neo.jump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            neo.moveRight();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            neo.moveLeft();
        }


    }

    @Override
    public void update(float dt) {
        handleInput();
        neo.update(dt);
        cam.position.x = neo.getPosition().x + CAMERA_OFFSET;

        for (int i = 0; i < enemies.size; i++) {

            Enemy enemy = enemies.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > enemy.getEnemyPosition().x + enemy.ENEMY_WIDTH) {

                enemy.update(dt);
            }

            if (enemy.getEnemyPosition().x + 2 * CAMERA_OFFSET - cam.position.x < 0){

                enemy.rePosition(enemy.getEnemyPosition().x + cam.viewportWidth);
            }

            if (enemy.collides(neo.getBounds())) {

                gsm.set(new PlayState(gsm));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);

        sb.begin();

        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);

        sb.draw(neo.getTexture(), neo.getPosition().x, neo.getPosition().y);

        for (Enemy enemy : enemies) {

            sb.draw(enemy.getEnemy(), enemy.getEnemyPosition().x, enemy.getEnemyPosition().y);
        }

        for (Vector2 groundPosition : groundPositions) {

            sb.draw(ground, groundPosition.x, groundPosition.y);
        }
        sb.end();

    }

    @Override
    public void dispose() {

        bg.dispose();
        neo.dispose();
        for (Enemy enemy : enemies) {

            enemy.dispose();
        }
    }
}
