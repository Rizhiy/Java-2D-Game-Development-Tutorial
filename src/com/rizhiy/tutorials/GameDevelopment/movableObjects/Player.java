package com.rizhiy.tutorials.GameDevelopment.movableObjects;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Check;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameState;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Player implements KeyListener {

    private double acceleration = 1;
    private double deceleration = 1;

    Vector2D position;

    public static int TileSize = 42;

    private static boolean up, right, down, left;
    private double maxSpeed = 300;

    private boolean mapMove = false;

    /**
     * UP,DOWN,LEFT,RIGHT
     */
    private double[] speeds = {0, 0, 0, 0};

    //TODO: choose a better name
    public enum SPEED {
        UP(0), DOWN(1), LEFT(2), RIGHT(3);

        private int code;

        SPEED(int c) {
            code = c;
        }

        public int getCode() {
            return code;
        }
    }


    public Player() {
        position = new Vector2D((Main.width) / 2, (Main.height) / 2);
    }

    public void init() {
    }

    private void updateSpeed(boolean direction, int index, double deltaTime) {
        if (direction) {
            if (speeds[index] < maxSpeed) speeds[index] += maxSpeed*acceleration * deltaTime;
            else speeds[index] = maxSpeed;
        } else {
            if (speeds[index] > 0) speeds[index] -= maxSpeed * deceleration * deltaTime;
            else speeds[index] = 0;
        }
    }

    private boolean checkCollision(SPEED d, double change) {
        Point p1 = null, p2 = null;
        switch (d) {
            case UP:
                p1 = new Point((int) (position.getX() + GameState.mapPosition.getX()), (int) (position.getY() + GameState.mapPosition.getY() - change));
                p2 = new Point((int) (position.getX() + GameState.mapPosition.getX()) + TileSize, (int) ((position.getY() + GameState.mapPosition.getY()) - change));
                break;
            case DOWN:
                p1 = new Point((int) (position.getX() + GameState.mapPosition.getX()), (int) ((position.getY() + GameState.mapPosition.getY()) + TileSize + change));
                p2 = new Point((int) (position.getX() + GameState.mapPosition.getX()) + TileSize, (int) ((position.getY() + GameState.mapPosition.getY()) + TileSize + change));
                break;
            case LEFT:
                p1 = new Point((int) ((position.getX() + GameState.mapPosition.getX()) - change), (int) ((position.getY() + GameState.mapPosition.getY())));
                p2 = new Point((int) ((position.getX() + GameState.mapPosition.getX()) - change), (int) ((position.getY() + GameState.mapPosition.getY()) + TileSize));
                break;
            case RIGHT:
                p1 = new Point((int) ((position.getX() + GameState.mapPosition.getX()) + TileSize + change), (int) ((position.getY() + GameState.mapPosition.getY())));
                p2 = new Point((int) ((position.getX() + GameState.mapPosition.getX()) + TileSize + change), (int) ((position.getY() + GameState.mapPosition.getY()) + TileSize));
                break;
        }
        return Check.CollisionPlayerBlock(p1, p2);
    }

    private void updateVector(Vector2D vector, double deltaTime) {
        updateSpeed(up, SPEED.UP.getCode(), deltaTime);
        updateSpeed(down, SPEED.DOWN.getCode(), deltaTime);
        updateSpeed(left, SPEED.LEFT.getCode(), deltaTime);
        updateSpeed(right, SPEED.RIGHT.getCode(), deltaTime);

        double changeU = speeds[SPEED.UP.getCode()] * deltaTime;
        double changeD = speeds[SPEED.DOWN.getCode()] * deltaTime;
        double changeL = speeds[SPEED.LEFT.getCode()] * deltaTime;
        double changeR = speeds[SPEED.RIGHT.getCode()] * deltaTime;

        if (!checkCollision(SPEED.UP, changeU)) vector.changeY(-changeU);
        else speeds[SPEED.UP.getCode()] = 0;
        if (!checkCollision(SPEED.DOWN, changeD)) vector.changeY(changeD);
        else speeds[SPEED.DOWN.getCode()] = 0;
        if (!checkCollision(SPEED.LEFT, changeL)) vector.changeX(-changeL);
        else speeds[SPEED.LEFT.getCode()] = 0;
        if (!checkCollision(SPEED.RIGHT, changeR)) vector.changeX(changeR);
        else speeds[SPEED.RIGHT.getCode()] = 0;
    }

    public void tick(double deltaTime) {

        if (!mapMove) {
            updateVector(position, deltaTime);
        } else {
            updateVector(GameState.mapPosition, deltaTime);
        }

    }

    public void render(Graphics2D g) {
        g.fillRect((int) (position.getX()), (int) (position.getY()), TileSize, TileSize);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
        }

    }
}
