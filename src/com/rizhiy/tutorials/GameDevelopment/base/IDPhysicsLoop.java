package com.rizhiy.tutorials.GameDevelopment.base;

/**
 * Created by rizhiy on 27/04/16.
 */
public abstract class IDPhysicsLoop extends BasicLoop {

    public IDPhysicsLoop(int refreshRate) {
        super(refreshRate);
    }

    @Override
    public void work(double deltaTime){
        tick(deltaTime);
    }

    public abstract void tick(double deltaTime);
}
