package com.rizhiy.tutorials.GameDevelopment.gameLoop;

import com.rizhiy.tutorials.GameDevelopment.base.IDPhysicsLoop;
import com.rizhiy.tutorials.GameDevelopment.base.IDRenderLoop;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameState;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by rizhiy on 27/04/16.
 */
public class RenderLoop extends IDRenderLoop {

    public GameState gameState;

    public IDPhysicsLoop renderLoop;

    public RenderLoop(int width, int height, int refreshRate, GameState state, PhysicsLoop renderLoop) {
        super(width, height, refreshRate);
        this.gameState = state;
        this.renderLoop = renderLoop;
    }

    @Override
    public void init() {
        super.init();
    }


    @Override
    public void render() {
        graphics2D.clipRect(0, 0, Main.width, Main.height);
        super.render();
        gameState.render(graphics2D);
        graphics2D.drawString("FPS: " + getCurrentRefreshRate() + ", TPS: " + renderLoop.getCurrentRefreshRate(), 200, 200);
        graphics2D.drawString("Player Position: " + GameState.map.getPlayer().getPosition(), 200, 300);
        graphics2D.drawString("Zoom: " + GameState.zoomLevel, 200, 400);
        clear();
    }

    @Override
    public void clear() {
        super.clear();
    }

    public void drawPicture(BufferedImage img, int x, int y, int width, int height) {
        Point[] vertices = {new Point(x, y),
                            new Point(x + width, y),
                            new Point(x + width, y + height),
                            new Point(x, y + height)};
        boolean shouldDraw = false;
        for(Point vertex : vertices){
            if(isInside(vertex)) shouldDraw = true;
        }
        if(shouldDraw){
            graphics2D.drawImage(img, x, y, width, height, null);
        }
    }
}
