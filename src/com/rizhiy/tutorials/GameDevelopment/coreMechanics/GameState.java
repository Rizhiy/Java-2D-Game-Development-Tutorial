package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import java.awt.*;

/**
 * Created by rizhiy on 23/04/16.
 */
public abstract class GameState {

    GameStateManager StateManager;
    public GameState(GameStateManager gsm){
        StateManager = gsm;

    }

    public abstract void init();
    public abstract void tick(double deltaTime);
    public abstract void render(Graphics2D g);
}
