package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.GameWindow;
import com.rizhiy.tutorials.GameDevelopment.base.SpriteSheet;
import com.rizhiy.tutorials.GameDevelopment.gameLoop.GameLoop;

/**
 * Created by rizhiy on 22/04/16.
 */
public class Main {
    public static void main(String[] args){
        GameWindow mainWindow = new GameWindow();
        mainWindow.setFullScreen(0);
        mainWindow.add(new GameLoop(720,480));
        mainWindow.setVisible(true);
    }
}
