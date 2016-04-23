package com.rizhiy.tutorials.GameDevelopment.base;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by rizhiy on 23/04/16.
 */
public class IDGameLoop extends JPanel implements Runnable {
    private Thread mainThread;
    private boolean running;

    private int FPS;
    private int TPS;

    private int width;
    private int height;

    public Graphics2D graphics2D;
    private BufferedImage background;

    public static double currentFPS = 60;

    public IDGameLoop(int width, int height) {
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setFocusable(false);
        requestFocus();
    }


    @Override
    public void addNotify() {
        super.addNotify();
        if (mainThread == null) {
            mainThread = new Thread(this);
            mainThread.start();
        }
    }

    @Override
    public void run() {

        /*INIT*/
        init();

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000 / currentFPS;
        int frames = 0;
        int ticks = 0;
        long lastTimer = System.currentTimeMillis();

        //Adjust computation if going slow or fast
        double deltaTime = 0;

        while (running) {
            long now = System.nanoTime();
            deltaTime += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = false;

            while (deltaTime >= 1) {
                ticks++;
                /*TICK + DELTATIME*/
                tick(deltaTime);
                deltaTime -= 1;
                shouldRender = true;
            }

            if (shouldRender) {
                /*RENDER*/
                frames++;
                render();
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                TPS = frames;
                FPS = ticks;
                frames = 0;
                ticks = 0;
            }
        }

    }

    public void init() {
        background = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) background.getGraphics();
        running = true;
    }

    public void tick(double deltaTime) {
    }

    public void render() {
        graphics2D.clearRect(0,0,width,height);
    }

    public void clear(){
        Graphics g2 = getGraphics();
        if(background != null){
            g2.drawImage(background,0,0,null);
        }
    }


}
