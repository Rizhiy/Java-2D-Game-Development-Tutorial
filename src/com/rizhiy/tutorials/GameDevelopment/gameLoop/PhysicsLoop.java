package com.rizhiy.tutorials.GameDevelopment.gameLoop;

import com.rizhiy.tutorials.GameDevelopment.base.IDPhysicsLoop;;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.GameState;

/**
 * Created by rizhiy on 27/04/16.
 */
public class PhysicsLoop extends IDPhysicsLoop {

    GameState gameState;
    public PhysicsLoop(GameState state,int refreshRate){
        super(refreshRate);
        gameState = state;
    }
    @Override
    public void tick(double deltaTime) {
        gameState.tick(deltaTime);
    }

    @Override
    public void init() {

    }
}
