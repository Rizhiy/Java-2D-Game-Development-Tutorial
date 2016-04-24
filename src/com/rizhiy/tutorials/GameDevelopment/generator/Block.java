package com.rizhiy.tutorials.GameDevelopment.generator;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Block extends Rectangle {
    Vector2D position = new Vector2D();

    private BlockType type;
    private BufferedImage img;

    public Block(Vector2D pos,BlockType type){
        this.position = pos;
        this.type = type;
        init();
    }

    public void init(){
        switch (type){
            case STONE_1:
                img = Assets.getStone_1();
                break;
        }
    }
    public void tick(double deltaTime){

    }
    public void render(Graphics2D g){
        g.drawImage(img, (int) (position.getWorldLocation().getX()*Map.TileSize), (int) (position.getWorldLocation().getY()*Map.TileSize),Map.TileSize,Map.TileSize,null);
    }

    public enum BlockType {
        STONE_1
    }
}
