package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

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
    }

    @Override
    public void tick(double deltaTime) {
        map.tick(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        g.drawString("Hello World!", 200,200);
        map.render(g);
    }
}
