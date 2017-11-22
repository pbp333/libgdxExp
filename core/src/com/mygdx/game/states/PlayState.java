package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MatrixGame;
import com.mygdx.game.Sprites.Neo;
import com.mygdx.game.Sprites.Enemy;

public class PlayState extends State {

    private static final int ENEMY_SPACING = 125;
    private static final int ENEMY_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -20;

    private Neo neo;
    private Texture bg;
    private Texture ground;

    private Vector2 groundPos1, groundPos2, groundPos3;

    private Array<Enemy> enemies;

    public PlayState(GameStateManager gsm) {

        super(gsm);
        neo = new Neo(0, 300);
        cam.setToOrtho(false, MatrixGame.WIDTH / 2, MatrixGame.HEIGHT / 2);
        bg = new Texture("matrixcity.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        groundPos3 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth() * 2, GROUND_Y_OFFSET);

        enemies = new Array<Enemy>();

        for (int i = 1; i < ENEMY_COUNT; i++) {

            enemies.add(new Enemy(i * (ENEMY_SPACING * 2 + Enemy.ENEMY_WIDTH)));
        }
    }

    @Override
    public void handleInput() {

        if (Gdx.input.justTouched()) {
            neo.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        upDateGround();
        neo.update(dt);
        cam.position.x = neo.getPosition().x + 200;

        for (int i = 0; i < enemies.size; i++) {

            Enemy enemy = enemies.get(i);

            if (cam.position.x - (cam.viewportWidth / 2) > enemy.getEnemyPosition().x + enemy.ENEMY_WIDTH) {

                enemy.update(dt);
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

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(ground, groundPos3.x, groundPos3.y);
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

    private void upDateGround(){

        if(cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.getWidth()){

            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.getWidth()){

            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
