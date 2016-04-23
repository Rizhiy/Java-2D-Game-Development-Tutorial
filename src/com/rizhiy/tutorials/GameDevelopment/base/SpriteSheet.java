package com.rizhiy.tutorials.GameDevelopment.base;

import java.awt.image.BufferedImage;

/**
 * Created by rizhiy on 23/04/16.
 */
public class SpriteSheet {
    private BufferedImage spriteSheet;

    public SpriteSheet(){

    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public BufferedImage getTile(int x, int y, int width, int height){
        BufferedImage sprite = spriteSheet.getSubimage(x,y,width,height);
        return sprite;
    }
}
