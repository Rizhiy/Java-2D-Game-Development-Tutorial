package com.rizhiy.tutorials.GameDevelopment.movableObjects;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Player implements KeyListener {

    Vector2D position;

    public static int TileSize = 42;

    private static boolean up,right,down,left;
    private double speed = 10.0;

    private double fixDt = 24.0/60;

    public Player(){
        position = new Vector2D(Main.width/2 - TileSize/2, Main.height/2 - TileSize/2);
    }

    public void init() {
    }

    public void tick(double deltaTime) {
        double change = speed*fixDt;
        if(up) {
            position.changeY(-change);
        }
        if(down) {
            position.changeY(change);
        }
        if(right){
            position.changeX(change);
        }
        if(left){
            position.changeX(-change);
        }
    }

    public void render(Graphics2D g) {
        g.fillRect((int)position.getX(),(int)position.getY(),TileSize,TileSize);
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
