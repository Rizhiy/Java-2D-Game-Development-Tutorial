package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.gameLoop.GameLoop;
import com.rizhiy.tutorials.GameDevelopment.generator.Map;

import java.awt.*;

/**
 * Created by rizhiy on 23/04/16.
 */
public class LevelLoader extends GameState{

    Map map;


    public LevelLoader(GameStateManager gsm){
        super(gsm);
    }
    @Override
    public void init() {
        map = new Map();
        map.init();
        GameLoop.map = new Vector2D((Map.width-Main.width/Map.TileSize)/2,(Map.height-Main.height/Map.TileSize)/2);
    }

    @Override
    public void tick(double deltaTime) {
        map.tick(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        map.render(g);
    }
}
