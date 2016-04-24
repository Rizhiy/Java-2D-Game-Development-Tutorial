package com.rizhiy.tutorials.GameDevelopment.generator;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by rizhiy on 24/04/16.
 */
public class TileManager {

    public ArrayList<Block> blocks = new ArrayList<>();
    public TileManager(){

    }

    public void tick(double deltaTime){
        for(Block block: blocks){
            block.tick(deltaTime);
        }
    }

    public void render(Graphics2D g){
        for(Block block: blocks){
            block.render(g);
        }
    }
}
