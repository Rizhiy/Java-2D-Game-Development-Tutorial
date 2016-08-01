package com.rizhiy.tutorials.GameDevelopment.movableObjects;

import com.rizhiy.GameEngine.Actor;
import com.rizhiy.GameEngine.Vector2D;
import com.rizhiy.GameEngine.World;

public class Player extends Actor {
    public Player(World host){
        super(host);
        this.setSize(new Vector2D(0.8,0.8));
    }

}
