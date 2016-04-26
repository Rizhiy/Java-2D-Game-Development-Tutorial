package com.rizhiy.tutorials.GameDevelopment.generator;

import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.coreMechanics.Assets;

import java.awt.*;
/**
 * Created by rizhiy on 24/04/16.
 */
public class Block extends Rectangle {
    Vector2D position = new Vector2D();

    private BlockType type;
    private boolean solid;

    public Block(Vector2D pos,BlockType type, boolean solid){
        this.position = pos;
        this.type = type;
        this.solid = solid;
        init();
    }

    public Block(Vector2D pos,BlockType type){
        this(pos,type,false);
    }

    public void init(){
        this.x = (int) position.getX();
        this.y = (int) position.getY();
        this.height = Map.TileSize;
        this.width = Map.TileSize;

    }
    public void tick(double deltaTime){

    }
    public void render(Graphics2D g){
        g.drawImage(Assets.getImage(type), (int) (position.getWorldLocation().getX()), (int) (position.getWorldLocation().getY()),Map.TileSize,Map.TileSize,null);
    }

    public boolean isSolid() {
        return solid;
    }

    public enum BlockType {
        STONE_1,
        WALL_1
    }
}
