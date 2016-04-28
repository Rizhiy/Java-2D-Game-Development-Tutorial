package com.rizhiy.tutorials.GameDevelopment.movableObjects;

import com.intellij.util.containers.IntObjectLinkedMap;
import com.rizhiy.tutorials.GameDevelopment.base.ImageLoader;
import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Animator;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Assets;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Check;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameState;
import com.rizhiy.tutorials.GameDevelopment.generator.Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Player implements KeyListener {

    //ration by which maxSpeed we be reached. Higher values lead to faster acc/decelerations.
    private double acceleration = 1;
    private double deceleration = 3;

    private Vector2D position;

    private double size = 0.8;

    //TODO: look into replacing this with a map of moveDirection and mutable boolean object
    private boolean up, right, down, left;
    private double maxSpeed = 5;

    private HashMap<MoveDirection,Animator> sprites = new HashMap<>();

    /**
     * UP,DOWN,LEFT,RIGHT
     */
    private double[] speeds = {0, 0, 0, 0};

    //TODO: choose a better name
    public enum MoveDirection {
        UP(0), DOWN(1), LEFT(2), RIGHT(3),IDLE(5);

        private int code;

        MoveDirection(int c) {
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

        ArrayList<BufferedImage> spriteUP = new ArrayList<>();
        spriteUP.add(Assets.getPlayerAnimation(MoveDirection.UP,0));
        spriteUP.add(Assets.getPlayerAnimation(MoveDirection.UP,1));
        sprites.put(MoveDirection.UP,new Animator(spriteUP,200,true));

        ArrayList<BufferedImage> spriteDOWN = new ArrayList<>();
        spriteDOWN.add(Assets.getPlayerAnimation(MoveDirection.DOWN,0));
        spriteDOWN.add(Assets.getPlayerAnimation(MoveDirection.DOWN,1));
        sprites.put(MoveDirection.DOWN,new Animator(spriteDOWN,200,true));

        ArrayList<BufferedImage> spriteLEFT = new ArrayList<>();
        spriteLEFT.add(Assets.getPlayerAnimation(MoveDirection.LEFT,0));
        spriteLEFT.add(Assets.getPlayerAnimation(MoveDirection.LEFT,1));
        sprites.put(MoveDirection.LEFT,new Animator(spriteLEFT,200,true));

        ArrayList<BufferedImage> spriteRIGHT = new ArrayList<>();
        spriteRIGHT.add(Assets.getPlayerAnimation(MoveDirection.RIGHT,0));
        spriteRIGHT.add(Assets.getPlayerAnimation(MoveDirection.RIGHT,1));
        sprites.put(MoveDirection.RIGHT,new Animator(spriteRIGHT,200,true));

    }

    private void updateSpeed(boolean direction, int index, double deltaTime) {
        double change;
        if (direction) {
            change = maxSpeed * acceleration * deltaTime;
            if (speeds[index] + change < maxSpeed) speeds[index] += change;
            else speeds[index] = maxSpeed;
        } else {
            change = maxSpeed * deceleration * deltaTime;
            if (speeds[index] - change > 0) speeds[index] -= change;
            else speeds[index] = 0;
        }
    }

    private boolean checkCollision(MoveDirection d, double change) {
        Vector2D p1 = null, p2 = null;
        switch (d) {
            case UP:
                p1 = new Vector2D(position.getX(), position.getY() - change);
                p2 = new Vector2D(position.getX() + size, position.getY() - change);
                break;
            case DOWN:
                p1 = new Vector2D(position.getX(), position.getY() + size + change);
                p2 = new Vector2D(position.getX() + size, position.getY() + size + change);
                break;
            case LEFT:
                p1 = new Vector2D(position.getX() - change, position.getY());
                p2 = new Vector2D(position.getX() - change, position.getY() + size);
                break;
            case RIGHT:
                p1 = new Vector2D(position.getX() + size + change, position.getY());
                p2 = new Vector2D(position.getX() + size + change, position.getY() + size);
                break;
        }
        return Check.CollisionPlayerBlock(p1, p2);
    }

    private void updateVector(Vector2D vector, double deltaTime, boolean map) {
        updateSpeed(up, MoveDirection.UP.getCode(), deltaTime);
        updateSpeed(down, MoveDirection.DOWN.getCode(), deltaTime);
        updateSpeed(left, MoveDirection.LEFT.getCode(), deltaTime);
        updateSpeed(right, MoveDirection.RIGHT.getCode(), deltaTime);

        double changeU = speeds[MoveDirection.UP.getCode()] * deltaTime;
        double changeD = speeds[MoveDirection.DOWN.getCode()] * deltaTime;
        double changeL = speeds[MoveDirection.LEFT.getCode()] * deltaTime;
        double changeR = speeds[MoveDirection.RIGHT.getCode()] * deltaTime;

        if (map) {
            changeU *= -Map.getTileSize();
            changeD *= -Map.getTileSize();
            changeL *= -Map.getTileSize();
            changeR *= -Map.getTileSize();
        }

        if (!checkCollision(MoveDirection.UP, changeU)) vector.changeY(-changeU);
        else speeds[MoveDirection.UP.getCode()] = 0;
        if (!checkCollision(MoveDirection.DOWN, changeD)) vector.changeY(changeD);
        else speeds[MoveDirection.DOWN.getCode()] = 0;
        if (!checkCollision(MoveDirection.LEFT, changeL)) vector.changeX(-changeL);
        else speeds[MoveDirection.LEFT.getCode()] = 0;
        if (!checkCollision(MoveDirection.RIGHT, changeR)) vector.changeX(changeR);
        else speeds[MoveDirection.RIGHT.getCode()] = 0;
    }

    public void tick(double deltaTime) {
        Vector2D oldPosition = new Vector2D(position);
        updateVector(position, deltaTime, false);
        if(!position.equals(oldPosition)){
            if (!GameState.playerMove) updateVector(GameState.mapPosition, deltaTime, true);
        }

        for(HashMap.Entry<MoveDirection,Animator> animator : sprites.entrySet()){
            animator.getValue().update(System.currentTimeMillis());
        }
    }

    public void render(Graphics2D g) {
        BufferedImage img;
        if(up){
            img = sprites.get(MoveDirection.UP).getSprite();
        } else if(down){
            img = sprites.get(MoveDirection.DOWN).getSprite();
        } else if(left){
            img = sprites.get(MoveDirection.LEFT).getSprite();
        } else if(right) {
            img = sprites.get(MoveDirection.RIGHT).getSprite();
        } else{
            img = Assets.getPlayerAnimation(MoveDirection.IDLE,0);
        }

        GameState.drawPicture(position,img);

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                up = true;
                sprites.get(MoveDirection.UP).play();
                break;
            case KeyEvent.VK_S:
                down = true;
                sprites.get(MoveDirection.DOWN).play();
                break;
            case KeyEvent.VK_A:
                left = true;
                sprites.get(MoveDirection.LEFT).play();
                break;
            case KeyEvent.VK_D:
                right = true;
                sprites.get(MoveDirection.RIGHT).play();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                up = false;
                sprites.get(MoveDirection.UP).stop();
                break;
            case KeyEvent.VK_S:
                down = false;
                sprites.get(MoveDirection.DOWN).stop();
                break;
            case KeyEvent.VK_A:
                left = false;
                sprites.get(MoveDirection.LEFT).stop();
                break;
            case KeyEvent.VK_D:
                right = false;
                sprites.get(MoveDirection.RIGHT).stop();
                break;
        }

    }

    public Vector2D getPosition() {
        return position;
    }
}
