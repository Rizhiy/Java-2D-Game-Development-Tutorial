package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import java.awt.*;

/**
 * Created by rizhiy on 23/04/16.
 */
public class LevelLoader extends GameState{

    public LevelLoader(GameStateManager gsm){
        super(gsm);
    }
    @Override
    public void init() {

    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        g.drawString("Hello World!", 200,200);
    }
}
