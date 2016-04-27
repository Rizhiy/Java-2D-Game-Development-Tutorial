package com.rizhiy.tutorials.GameDevelopment.base;

import javax.swing.*;

/**
 * Created by rizhiy on 27/04/16.
 */
public abstract class BasicLoop extends JPanel implements Runnable{

    private int currentRefreshRate = 0;
    private final int desiredRefreshRate;
    private final double desiredTimePerIteration;
    private final long desiredMilliSecondsPerIteration;

    private long lastTime = System.currentTimeMillis();
    private int iterations = 0;
    private long lastUpdateTime = 0;

    public BasicLoop(int refreshRate){
        desiredRefreshRate = refreshRate;
        desiredTimePerIteration = 1.0 / desiredRefreshRate;
        desiredMilliSecondsPerIteration = (long) (desiredTimePerIteration * 1000.0);
    }


    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            //calculate next tick time
            long iterationStartTime = System.currentTimeMillis();
            long nextIterationTime = lastTime + desiredMilliSecondsPerIteration;


            //process everything that is required
            work((iterationStartTime - lastTime) / 1000.0);

            //update stats
            iterations++;
            lastTime = iterationStartTime;

            //check if we need to refresh stats
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > 1000) {
                setCurrentRefreshRate(iterations);
                iterations = 0;
                lastUpdateTime = currentTime;
            }

            //wait until next iteration time
            try {
                long timeToWait = nextIterationTime - System.currentTimeMillis();
                if(timeToWait > 0) Thread.sleep(timeToWait);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public abstract void work(double deltaTime);


    public synchronized int getCurrentRefreshRate() {
        return currentRefreshRate;
    }

    private synchronized void setCurrentRefreshRate(int ticks) {
        currentRefreshRate = ticks;
    }

    public abstract void init();
}
