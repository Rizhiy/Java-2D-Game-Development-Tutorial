package com.rizhiy.tutorials.GameDevelopment.main;

import com.rizhiy.GameEngine.Tile;
import com.rizhiy.GameEngine.TileType;
import com.rizhiy.GameEngine.World;

public class Block extends Tile {
    public enum BlockType implements TileType {WALL, FLOOR, WOOD_FLOOR}

    public Block(Block original) {
        super(original);
    }

    public Block(BlockType type, World world) {
        super(type, world);
        if (type == BlockType.FLOOR || type == BlockType.WOOD_FLOOR) {
            this.solid = false;
        }
    }

    @Override
    public void update(double v) {

    }

    @Override
    public Tile makeCopy() {
        return new Block(this);
    }
}
