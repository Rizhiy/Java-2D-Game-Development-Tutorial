package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.GameWindow;
import com.rizhiy.tutorials.GameDevelopment.gameLoop.GameLoop;
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
        GameWindow mainWindow = new GameWindow("A Game", width, height, monitor);
        mainWindow.setFullScreen(monitor);
        mainWindow.addKeyListener(new Player());
        mainWindow.add(new GameLoop(width,height));
        mainWindow.setVisible(true);
    }
}
