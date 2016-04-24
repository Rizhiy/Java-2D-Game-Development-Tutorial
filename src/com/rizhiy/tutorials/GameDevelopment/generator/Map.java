package com.rizhiy.tutorials.GameDevelopment.generator;

import com.rizhiy.tutorials.GameDevelopment.base.ImageLoader;
import com.rizhiy.tutorials.GameDevelopment.base.Vector2D;
import com.rizhiy.tutorials.GameDevelopment.movableObjects.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rizhiy on 24/04/16.
 */
public class Map {

    public static int TileSize = 48;

    Player player = new Player();

    public static final Path mapFilePath = Paths.get(System.getProperty("user.dir")+"/Sprites/map.png");

    TileManager tiles = new TileManager();

    private BufferedImage map;

    public Map(){

    }

    public void init(){
        player.init();
        map = null;
        map = ImageLoader.loadImageFrom(mapFilePath);
        for(int x = 0; x < map.getWidth(); x++){
            for(int y = 0; y < map.getHeight(); y++){
                int colour = map.getRGB(x,y);
                switch(colour & 0x505050){
                    case 0x505050:
                        tiles.blocks.add(new Block(new Vector2D(x,y), Block.BlockType.STONE_1));
                        break;
                }
            }
        }
    }

    public void tick(double deltaTime){
        tiles.tick(deltaTime);
        player.tick(deltaTime);

    }

    public void render(Graphics2D g){
        tiles.render(g);
        player.render(g);
    }
}
