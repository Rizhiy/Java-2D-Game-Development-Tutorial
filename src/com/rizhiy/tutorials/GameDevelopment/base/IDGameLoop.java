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

    protected int FPS;
    protected int TPS;

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
        double nsPerFrame = 1000000000 / currentFPS;
        int frames = 0;
        int ticks = 0;
        long lastTimer = System.currentTimeMillis();

        double timeUntilNextFrame;

        long prev = System.nanoTime();

        while (running) {
            do {
                //TODO: find out why we are three orders of magnitudes off
                tick((System.nanoTime() - prev)/1_000_000.0);
                timeUntilNextFrame = System.nanoTime() - lastTime;
                prev = System.nanoTime();
                ticks++;
            } while (timeUntilNextFrame < nsPerFrame);

                /*RENDER*/
            frames++;
            render();
            lastTime = System.nanoTime();

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                TPS = ticks;
                FPS = frames;
                frames = 0;
                ticks = 0;
                lastTimer = System.currentTimeMillis();
            }
            prev = System.nanoTime();
        }

    }

    public void init() {
        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) background.getGraphics();
        running = true;
    }

    public void tick(double deltaTime) {
    }

    public void render() {
        graphics2D.clearRect(0, 0, width, height);
    }

    public void clear() {
        Graphics g2 = getGraphics();
        if (background != null) {
            g2.drawImage(background, 0, 0, null);
        }
    }


}
