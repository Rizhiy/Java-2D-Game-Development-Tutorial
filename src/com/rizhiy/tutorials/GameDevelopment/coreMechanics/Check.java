package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.generator.Block;
import com.rizhiy.tutorials.GameDevelopment.generator.TileManager;

import java.awt.*;

/**
 * Created by rizhiy on 26/04/16.
 */
public class Check {

    public static boolean CollisionPlayerBlock(Vector2D p1, Vector2D p2){
        for(Block block: TileManager.blocks){
            if(block.isSolid()){
                if(block.contains(p1.getX(),p1.getY()) || block.contains(p2.getX(),p2.getY()))
                return true;
            }
        }

        return false;
    }
}
