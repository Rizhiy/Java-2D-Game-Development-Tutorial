package com.rizhiy.tutorials.GameDevelopment.base;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rizhiy on 22/04/16.
 */
public class GameWindow extends JFrame {
    boolean fullScreen = false;
    int fullScreenMode = 0;
    int display = 0;

    GraphicsDevice device = null;


    public GameWindow(String title, int width, int height, int display){
        setTitle(title);
        setSize(width,height);

        //position in the middle of the screen on start
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        this.display = display;
        setGraphicsDevice();
    }
    public GameWindow(String title, int width, int height){
        this(title,width,height,0);
    }
    public GameWindow(){
        this("Main Window",720,480);
    }

    private void setFullScreenMode(){
        switch(fullScreenMode){
            case 0:
                System.out.println("No Fullscreen");
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

    private void setGraphicsDevice(){
        if(device == null) return;
        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[display];
    }
}
