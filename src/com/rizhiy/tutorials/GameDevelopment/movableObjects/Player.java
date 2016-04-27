package com.rizhiy.tutorials.GameDevelopment.movableObjects;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Check;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameState;
import com.rizhiy.tutorials.GameDevelopment.generator.Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Player implements KeyListener {

    //ration by which maxSpeed we be reached. Higher values lead to faster acc/decelerations.
    private double acceleration = 1;
    private double deceleration = 1;

    private Vector2D position;

    public static double size = 0.8;

    private static boolean up, right, down, left;
    private double maxSpeed = 5;

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
    }

    public void init() {
        position = new Vector2D(Map.width / 2, Map.height / 2);
    }

    private void updateSpeed(boolean direction, int index, double deltaTime) {
        double change;
        if (direction) {
            change  = maxSpeed * acceleration * deltaTime;
            if (speeds[index] + change < maxSpeed) speeds[index] += change;
            else speeds[index] = maxSpeed;
        } else {
            change = maxSpeed * deceleration * deltaTime;
            if (speeds[index] - change > 0) speeds[index] -= change;
            else speeds[index] = 0;
        }
    }

    private boolean checkCollision(SPEED d, double change) {
        Vector2D p1 = null, p2 = null;
        switch (d) {
            case UP:
                p1 = new Vector2D(position.getX(), position.getY() - change);
                p2 = new Vector2D(position.getX() + size, position.getY() - change);
                break;
            case DOWN:
                p1 = new Vector2D(position.getX(),position.getY() + size + change);
                p2 = new Vector2D(position.getX() + size,position.getY() + size + change);
                break;
            case LEFT:
                p1 = new Vector2D(position.getX() - change,position.getY());
                p2 = new Vector2D(position.getX() - change,position.getY() + size);
                break;
            case RIGHT:
                p1 = new Vector2D(position.getX() + size + change,position.getY());
                p2 = new Vector2D(position.getX() + size + change,position.getY() + size);
                break;
        }
        return Check.CollisionPlayerBlock(p1, p2);
    }

    private void updateVector(Vector2D vector, double deltaTime,boolean map) {
        updateSpeed(up, SPEED.UP.getCode(), deltaTime);
        updateSpeed(down, SPEED.DOWN.getCode(), deltaTime);
        updateSpeed(left, SPEED.LEFT.getCode(), deltaTime);
        updateSpeed(right, SPEED.RIGHT.getCode(), deltaTime);

        double changeU = speeds[SPEED.UP.getCode()] * deltaTime;
        double changeD = speeds[SPEED.DOWN.getCode()] * deltaTime;
        double changeL = speeds[SPEED.LEFT.getCode()] * deltaTime;
        double changeR = speeds[SPEED.RIGHT.getCode()] * deltaTime;

        if(map){
            changeU *= -Map.getTileSize();
            changeD *= -Map.getTileSize();
            changeL *= -Map.getTileSize();
            changeR *= -Map.getTileSize();
        }

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
        updateVector(position, deltaTime,false);
        if(!GameState.playerMove) updateVector(GameState.mapPosition,deltaTime,true);
    }

    public void render(Graphics2D g) {
        g.fillRect((int) (position.getX() * Map.getTileSize() + GameState.mapPosition.getX()),
                   (int) (position.getY() * Map.getTileSize() + GameState.mapPosition.getY()),
                   (int) (Map.getTileSize()*size), (int) (Map.getTileSize()*size));
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
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
        switch (keyEvent.getKeyCode()) {
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

    public Vector2D getPosition(){
        return position;
    }
}
