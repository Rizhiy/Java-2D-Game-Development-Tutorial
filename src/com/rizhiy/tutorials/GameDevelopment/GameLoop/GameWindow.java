package com.rizhiy.tutorials.GameDevelopment.GameLoop;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rizhiy on 22/04/16.
 */
public class GameWindow extends JFrame {
    boolean fullScreen = false;
    int fullScreenMode = 0;

    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];


    public GameWindow(String title, int width, int height){
        setTitle(title);
        setSize(width,height);

        //position in the middle of the screen on start
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    public GameWindow(){
        this("Main Window",720,480);
    }

    private void setFullScreenMode(){
        switch(fullScreenMode){
            case 0:
                System.err.println("No Fullscreen");
                setUndecorated(false);
                break;
            case 1:
                setUndecorated(true);
                setExtendedState(MAXIMIZED_BOTH);
                break;
            case 2:
                setUndecorated(true);
                device.setFullScreenWindow(this);
                break;
            default:

        }
    }

    public void setFullScreen(int mode){
        fullScreen = true;
        if(mode < 3){
            this.fullScreenMode = mode;
            setFullScreenMode();
        } else {
            System.err.println("Error "+mode+" is not Supported!");
        }
    }
}
