package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Neo {

    private static final int GRAVITY = -5;
    private static final int POSITION_OFF_GROUND = 10;

    private Vector3 position;
    private Vector3 velocity;
    private Texture neoTexture;

    private Rectangle bounds;

    public Neo(int x, int y) {

    position = new Vector3(x, y + POSITION_OFF_GROUND, 0);

        velocity = new Vector3(0, 0, 0);

        neoTexture = new Texture("neoicon.png");

        bounds = new Rectangle(x, y, neoTexture.getWidth(), neoTexture.getHeight());
    }

    public void update(float dt) {

        if (position.y > POSITION_OFF_GROUND) {

            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(dt);
        position.add(velocity.x, velocity.y, 0);
        velocity.scl(1 / dt);

        bounds.setPosition(position.x, position.y);


        if (position.y < POSITION_OFF_GROUND) {

            position.y = POSITION_OFF_GROUND;
        }

    }

    public void dispose() {

        neoTexture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void jump() {

        if (position.y == POSITION_OFF_GROUND) {

            velocity.y = 300;
        }

    }

    public void moveRight(){

        position.x += 3;
    }

    public void moveLeft(){

        position.x -= 3;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Texture getTexture() {
        return neoTexture;
    }
}
