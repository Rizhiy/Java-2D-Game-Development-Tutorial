package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.ImageLoader;
import com.rizhiy.tutorials.GameDevelopment.base.SpriteSheet;
import com.rizhiy.tutorials.GameDevelopment.generator.Block;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Assets {

    public static final int textureSize = 16;
    public static final Path SPRITE_SHEET_PATH = Paths.get(System.getProperty("user.dir") + "/Sprites/SPRITESHEET.png");

    SpriteSheet blocks = new SpriteSheet();

    private static BufferedImage stone_1;
    private static BufferedImage wall_1;
    private static BufferedImage defaultImage;

    public void init() {
        blocks.setSpriteSheet(ImageLoader.loadImageFrom(SPRITE_SHEET_PATH));

        stone_1 = blocks.getTile(0, 0, textureSize, textureSize);
        wall_1 = blocks.getTile(0, 1, textureSize, textureSize);
        defaultImage = blocks.getTile(0,2,textureSize,textureSize);
    }

    public static BufferedImage getImage(Block.BlockType type) {
        switch (type) {
            case STONE_1:
                return stone_1;
            case WALL_1:
                return wall_1;
            default:
                return defaultImage;
        }
    }


}
