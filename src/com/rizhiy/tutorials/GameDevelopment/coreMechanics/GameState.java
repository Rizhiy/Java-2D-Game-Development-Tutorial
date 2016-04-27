package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.generator.Map;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by rizhiy on 23/04/16.
 */
public class GameState implements KeyListener {

    public static double zoomLevel = 1.5;
    private double zoomChangeRate = 1.001;

    public static Map map;

    public static Vector2D mapPosition = new Vector2D();

    public static boolean playerMove = true;

    private boolean increaseZoom,decreaseZoom;


    public GameState(){
        this.increaseZoom = false;
        this.decreaseZoom = false;
    }

    public void init() {
        map = new Map();
        map.init();
        mapPosition = new Vector2D((-Map.width*Map.getTileSize()+Main.width)/2,(-Map.height*Map.getTileSize()+Main.height)/2);
    }

    public synchronized void tick(double deltaTime) {
        map.tick(deltaTime);

        //TODO: fix zoom
        if(increaseZoom){
            mapPosition.changeX(+Map.width*Map.getTileSize());
            mapPosition.changeY(+Map.height*Map.getTileSize());
            zoomLevel *= zoomChangeRate;
            mapPosition.changeX(-Map.width*Map.getTileSize());
            mapPosition.changeY(-Map.height*Map.getTileSize());
        }
        if(decreaseZoom){
            mapPosition.changeX(+Map.width*Map.getTileSize());
            mapPosition.changeY(+Map.height*Map.getTileSize());
            zoomLevel /= zoomChangeRate;
            mapPosition.changeX(-Map.width*Map.getTileSize());
            mapPosition.changeY(-Map.height*Map.getTileSize());
        }
    }

    public synchronized void render(Graphics2D g) {
        map.render(g);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ADD:
                increaseZoom = true;
                break;
            case KeyEvent.VK_SUBTRACT:
                decreaseZoom = true;
                break;
            case KeyEvent.VK_ENTER:
                playerMove = false;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_ADD:
                increaseZoom = false;
                break;
            case KeyEvent.VK_SUBTRACT:
                decreaseZoom = false;
                break;
            case KeyEvent.VK_ENTER:
                playerMove = true;
                break;
        }
    }
}
