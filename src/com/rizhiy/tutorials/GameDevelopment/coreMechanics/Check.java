package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.generator.Block;
import com.rizhiy.tutorials.GameDevelopment.generator.TileManager;

import java.awt.*;

/**
 * Created by rizhiy on 26/04/16.
 */
public class Check {

    public static boolean CollisionPlayerBlock(Point p1, Point p2){
        for(Block block: TileManager.blocks){
            if(block.isSolid()){
                if(block.contains(p1) || block.contains(p2))
                return true;
            }
        }

        return false;
    }
}
