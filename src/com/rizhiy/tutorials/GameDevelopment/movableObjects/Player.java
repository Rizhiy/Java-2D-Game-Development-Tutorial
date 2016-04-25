package com.rizhiy.tutorials.GameDevelopment.movableObjects;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Main;
import com.rizhiy.tutorials.GameDevelopment.gameLoop.GameLoop;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Player implements KeyListener {
    
    private double acceleration = 0.005;
    private double deceleration = 0.005;

    Vector2D position;

    public static int TileSize = 42;

    private static boolean up,right,down,left;
    private double maxSpeed = 0.05;

    private boolean mapMove = true;

    /**
     * UP,DOWN,LEFT,RIGHT
     */
    private double[] speeds = {0,0,0,0};
    public enum SPEED {
        UP(0), DOWN(1), LEFT(2), RIGHT(3);

        private int code;
        SPEED(int c){
            code = c;
        }

        public int getCode(){
            return code;
        }
    }

    private double fixDt = 24.0/60;

    public Player(){
//        position = new Vector2D();
        position = new Vector2D((Main.width/TileSize)/2,(Main.height/TileSize)/2);
    }

    public void init() {
    }

    private void updateSpeed(boolean direction, int index){
        if(direction) {
            if(speeds[index] < maxSpeed) speeds[index] += acceleration;
            else speeds[index] = maxSpeed;
        } else {
            if(speeds[index] > 0) speeds[index] -= deceleration;
            else speeds[index] = 0;
        }
    }

    private void updateVector(Vector2D vector,double deltaTime){
        double changeU = speeds[SPEED.UP.getCode()]*deltaTime;
        double changeD = speeds[SPEED.DOWN.getCode()]*deltaTime;
        double changeL = speeds[SPEED.LEFT.getCode()]*deltaTime;
        double changeR = speeds[SPEED.RIGHT.getCode()]*deltaTime;

        updateSpeed(up,SPEED.UP.getCode());
        updateSpeed(down,SPEED.DOWN.getCode());
        updateSpeed(left,SPEED.LEFT.getCode());
        updateSpeed(right,SPEED.RIGHT.getCode());

        vector.changeY(-changeU);
        vector.changeY(changeD);
        vector.changeX(changeR);
        vector.changeX(-changeL);
    }

    public void tick(double deltaTime) {

        if(!mapMove){
            updateVector(position,deltaTime);
        } else {
            updateVector(GameLoop.map,deltaTime);
        }

    }

    public void render(Graphics2D g) {
        g.fillRect((int) (position.getX()*TileSize),(int) (position.getY()*TileSize),TileSize,TileSize);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        switch (key){
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

        switch (key){
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
