package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

    private static final int ENEMY_GROUND_DISTANCE = 0;
    private static final int LOWEST_OPENING = 120;
    public static final int ENEMY_WIDTH = 52;

    private Texture enemy;
    private Vector2 position;
    private Vector2 velocity;

    private Rectangle boundsEnemy;

    public Enemy(float x) {

        enemy = new Texture("smithicon.png");
        //enemy = new Texture("morpheus.png");

        position = new Vector2(x, ENEMY_GROUND_DISTANCE);

        velocity = new Vector2(75, 0);

        boundsEnemy = new Rectangle(position.x, position.y, enemy.getWidth(), enemy.getHeight());

    }

    public Texture getEnemy() {
        return enemy;
    }

    public Vector2 getEnemyPosition() {
        return position;
    }

    public void rePosition(float x) {

        position.set(x, 0);

        boundsEnemy.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle player) {

        return player.overlaps(boundsEnemy) || player.overlaps(boundsEnemy);
    }

    public void dispose() {

        enemy.dispose();
    }


    public void update(float dt) {

        velocity.scl(dt);
        position.add(velocity.x, velocity.y);
        velocity.scl(1 / dt);
    }
}
