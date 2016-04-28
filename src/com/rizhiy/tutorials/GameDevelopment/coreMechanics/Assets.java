package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.ImageLoader;
import com.rizhiy.tutorials.GameDevelopment.base.SpriteSheet;
import com.rizhiy.tutorials.GameDevelopment.generator.Block;
import com.rizhiy.tutorials.GameDevelopment.movableObjects.Player;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Assets {

    public static final int textureSize = 16;
    private static final Path SPRITE_SHEET_PATH = Paths.get(System.getProperty("user.dir") + "/Sprites/SPRITESHEET.png");
    private static final Path PLAYER_SHEET_PATH = Paths.get(System.getProperty("user.dir") + "/Sprites/PLAYERSHEET.png");

    private static SpriteSheet blocks = new SpriteSheet();
    private static SpriteSheet player = new SpriteSheet();

    private static Map<Block.BlockType,BufferedImage> tiles = new HashMap<>();

    public static void init() {
        blocks.setSpriteSheet(ImageLoader.loadImageFrom(SPRITE_SHEET_PATH));
        player.setSpriteSheet(ImageLoader.loadImageFrom(PLAYER_SHEET_PATH));

        tiles.put(Block.BlockType.STONE_1,blocks.getTile(0, 0, textureSize, textureSize));
        tiles.put(Block.BlockType.WALL_1,blocks.getTile(0, 1, textureSize, textureSize));
        tiles.put(Block.BlockType.WOOD_FLOOR_1,blocks.getTile(0, 2, textureSize, textureSize));
    }

    public static BufferedImage getBlock(Block.BlockType type) {
        return tiles.get(type);
    }

    public static BufferedImage getPlayerAnimation(Player.MoveDirection direction, int frame){
        switch (direction){
            case UP:
                return player.getTile(frame,0,textureSize,textureSize);
            case DOWN:
                return player.getTile(frame,1,textureSize,textureSize);
            case LEFT:
                return player.getTile(frame,3,textureSize,textureSize);
            case RIGHT:
                return player.getTile(frame,4,textureSize,textureSize);
            case IDLE:
                return player.getTile(frame,2,textureSize,textureSize);
            default:
                throw new IndexOutOfBoundsException();
        }
    }


}
