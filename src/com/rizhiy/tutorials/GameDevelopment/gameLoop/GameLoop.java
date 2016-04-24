package com.rizhiy.tutorials.GameDevelopment.gameLoop;

import com.rizhiy.tutorials.GameDevelopment.base.IDGameLoop;
import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Assets;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameStateManager;

import java.awt.*;

/**
 * Created by rizhiy on 23/04/16.
 */
public class GameLoop extends IDGameLoop {

    GameStateManager StateManager;

    public static Assets assets = new Assets();

    public static double xOffset;
    public static double yOffset;

    public GameLoop(int width, int height) {
        super(width,height);
    }

    @Override
    public void init(){
        assets.init();
        Vector2D.setWorldCoordinates(xOffset,yOffset);
        StateManager = new GameStateManager();
        StateManager.init();
        super.init();
    }

    @Override
    public void tick(double deltaTime){
        StateManager.tick(deltaTime);
    }

    @Override
    public void render(){
        super.render();
        StateManager.render(graphics2D);
        clear();
    }

    @Override
    public void clear(){
        super.clear();
    }
}
