package com.rizhiy.tutorials.GameDevelopment.gameLoop;

import com.rizhiy.tutorials.GameDevelopment.base.IDRenderLoop;
import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Assets;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameState;

/**
 * Created by rizhiy on 27/04/16.
 */
public class RenderLoop extends IDRenderLoop {

    public GameState gameState;

    public static Assets assets = new Assets();

    public static Vector2D map = new Vector2D();

    public RenderLoop(int width, int height,int refreshRate, GameState state) {
        super(width,height,refreshRate);
        gameState = state;
    }

    @Override
    public void init(){
        assets.init();
        Vector2D.setWorldCoordinates(map.getX(),map.getY());
        super.init();
    }


    @Override
    public void render(){
        super.render();
        gameState.render(graphics2D);
        graphics2D.drawString("FPS: "+getCurrentRefreshRate(),200,200);
        clear();

    }

    @Override
    public void clear(){
        super.clear();
    }
}
