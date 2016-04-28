package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.GameWindow;
import com.rizhiy.tutorials.GameDevelopment.gameLoop.PhysicsLoop;
import com.rizhiy.tutorials.GameDevelopment.gameLoop.RenderLoop;
import com.rizhiy.tutorials.GameDevelopment.movableObjects.Player;

import java.awt.*;

/**
 * Created by rizhiy on 22/04/16.
 */
public class Main {

    public static int monitor = 1;

    public static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[monitor];
    public static int width = gd.getDisplayMode().getWidth();
    public static int height = gd.getDisplayMode().getHeight();
    public static int display = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length;

    public static void main(String[] args){
        Assets.init();
        GameState state = new GameState();
        state.init();
        PhysicsLoop physicsLoop = new PhysicsLoop(state,100);
        physicsLoop.init();
        RenderLoop renderLoop = new RenderLoop(width,height,60,state,physicsLoop);
        renderLoop.init();
        state.addLoops(renderLoop,physicsLoop);





        GameWindow mainWindow = new GameWindow("A Game", width, height, monitor);
        mainWindow.setFullScreen(monitor);
        mainWindow.addKeyListener(GameState.map.getPlayer());
        mainWindow.addKeyListener(state);
        mainWindow.add(renderLoop);

        Thread physicsLoopThread = new Thread(physicsLoop);
        Thread renderLoopThread = new Thread(renderLoop);
        physicsLoopThread.start();
        renderLoopThread.start();

        mainWindow.setVisible(true);
    }
}
