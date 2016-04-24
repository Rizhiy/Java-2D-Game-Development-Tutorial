package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import com.rizhiy.tutorials.GameDevelopment.base.ImageLoader;
import com.rizhiy.tutorials.GameDevelopment.base.SpriteSheet;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Assets {

    public static final int textureSize = 16;
    public static final Path SPRITE_SHEET_PATH = Paths.get(System.getProperty("user.dir")+"/Sprites/SPRITESHEET.png");

    SpriteSheet blocks = new SpriteSheet();

    private static BufferedImage stone_1;

    public void init(){
        blocks.setSpriteSheet(ImageLoader.loadImageFrom(SPRITE_SHEET_PATH));

        stone_1 = blocks.getTile(0,0,textureSize,textureSize);
    }

    public static BufferedImage getStone_1(){
        return stone_1;
    }

}
