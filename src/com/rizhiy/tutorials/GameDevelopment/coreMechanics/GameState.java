package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.generator.Map;

import java.awt.*;

/**
 * Created by rizhiy on 23/04/16.
 */
public class GameState {

    static Map map;

    public static Vector2D mapPosition = new Vector2D();


    public GameState(){

    }

    public void init() {
        map = new Map();
        map.init();
        mapPosition = new Vector2D((Map.width*Map.TileSize-Main.width)/2,(Map.height*Map.TileSize-Main.height)/2);
    }

    public void tick(double deltaTime) {
        map.tick(deltaTime);
    }

    public void render(Graphics2D g) {
        map.render(g);
    }
}
