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
    private double zoomChangeRate = 1.005;

    public static Map map;

    public static Vector2D mapPosition = new Vector2D();

    public static boolean playerMove = true;

    private boolean increaseZoom, decreaseZoom;


    public GameState() {
        this.increaseZoom = false;
        this.decreaseZoom = false;
    }

    public void init() {
        map = new Map();
        map.init();
        mapPosition = new Vector2D(Main.width / 2 - map.getPlayer().getPosition().getX() * Map.getTileSize(),
                                   Main.height / 2 - map.getPlayer().getPosition().getY() * Map.getTileSize());
    }

    public synchronized void tick(double deltaTime) {
        map.tick(deltaTime);

        if (increaseZoom) {
            mapPosition.changeX(+map.getPlayer().getPosition().getX() * Map.getTileSize());
            mapPosition.changeY(+map.getPlayer().getPosition().getY() * Map.getTileSize());
            zoomLevel *= zoomChangeRate;
            mapPosition.changeX(-map.getPlayer().getPosition().getX() * Map.getTileSize());
            mapPosition.changeY(-map.getPlayer().getPosition().getY() * Map.getTileSize());
        }
        if (decreaseZoom) {
            mapPosition.changeX(+map.getPlayer().getPosition().getX() * Map.getTileSize());
            mapPosition.changeY(+map.getPlayer().getPosition().getY() * Map.getTileSize());
            zoomLevel /= zoomChangeRate;
            mapPosition.changeX(-map.getPlayer().getPosition().getX() * Map.getTileSize());
            mapPosition.changeY(-map.getPlayer().getPosition().getY() * Map.getTileSize());
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
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
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
