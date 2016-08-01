package com.rizhiy.tutorials.GameDevelopment.main;

import com.rizhiy.GameEngine.*;
import com.rizhiy.GameEngine.Action;
import com.rizhiy.tutorials.GameDevelopment.movableObjects.Player;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Tile> tileCodes = new HashMap<>();
        tileCodes.put(0x404040, new Block(Block.BlockType.WALL, null));
        tileCodes.put(0x402000, new Block(Block.BlockType.WOOD_FLOOR, null));
        tileCodes.put(0xa0a0a0, new Block(Block.BlockType.FLOOR, null));

        World      mainWorld      = new World(new File("/home/rizhiy/IdeaProjects/2DGameDevelopmentTutorial/Sprites/map.png"), tileCodes);
        Actor      player         = new Player(mainWorld);
        WorldState mainWorldState = new WorldState(mainWorld, player);

        GameWindow mainWindow = new GameWindow("Test", 1);

        Assets      mainAssets        = mainWindow.getAssets();
        SpriteSheet playerSpriteSheet = mainAssets.addSpriteSheet(new File("/home/rizhiy/IdeaProjects/2DGameDevelopmentTutorial/Sprites/PLAYERSHEET.png"));

        //Player sprites
        ArrayList<BufferedImage> spriteUP = new ArrayList<>();
        spriteUP.add(playerSpriteSheet.getTile(0, 0, 1, 1));
        spriteUP.add(playerSpriteSheet.getTile(1, 0, 1, 1));
        player.addAnimation(Actor.MoveDirection.UP, new Animator(spriteUP, 200, true));

        ArrayList<BufferedImage> spriteDOWN = new ArrayList<>();
        spriteDOWN.add(playerSpriteSheet.getTile(0, 1, 1, 1));
        spriteDOWN.add(playerSpriteSheet.getTile(1, 1, 1, 1));
        player.addAnimation(Actor.MoveDirection.DOWN, new Animator(spriteDOWN, 200, true));

        ArrayList<BufferedImage> spriteIDLE = new ArrayList<>();
        spriteIDLE.add(playerSpriteSheet.getTile(0, 2, 1, 1));
        player.addAnimation(Actor.MoveDirection.IDLE, new Animator(spriteIDLE, 200, false));

        ArrayList<BufferedImage> spriteLEFT = new ArrayList<>();
        spriteLEFT.add(playerSpriteSheet.getTile(0, 3, 1, 1));
        spriteLEFT.add(playerSpriteSheet.getTile(1, 3, 1, 1));
        player.addAnimation(Actor.MoveDirection.LEFT, new Animator(spriteLEFT, 200, true));

        ArrayList<BufferedImage> spriteRIGHT = new ArrayList<>();
        spriteRIGHT.add(playerSpriteSheet.getTile(0, 4, 1, 1));
        spriteRIGHT.add(playerSpriteSheet.getTile(1, 4, 1, 1));
        player.addAnimation(Actor.MoveDirection.RIGHT, new Animator(spriteRIGHT, 200, true));


        //Block Sprites
        SpriteSheet blockSpriteSheet = mainAssets.addSpriteSheet(new File(System.getProperty("user.dir") + "/Sprites/SPRITESHEET.png"));
        mainAssets.addTile(Block.BlockType.FLOOR, blockSpriteSheet.getTile(0, 0, 1, 1));
        mainAssets.addTile(Block.BlockType.WALL, blockSpriteSheet.getTile(0, 1, 1, 1));
        mainAssets.addTile(Block.BlockType.WOOD_FLOOR, blockSpriteSheet.getTile(0, 2, 1, 1));

        mainWindow.getConfig().setZoomLevel(1);
        mainWindow.getConfig().setFollowTarget(player);

        Loop   mainLoop   = new Loop(0.01, 60, mainWorldState, mainWindow);
        Engine mainEngine = new Engine(mainWorldState, mainLoop);

        HashMap<Integer,Action> keyBindings = new HashMap<>();
        keyBindings.put(KeyEvent.VK_UP,Action.CAMERA_MOVE_UP);
        keyBindings.put(KeyEvent.VK_DOWN,Action.CAMERA_MOVE_DOWN);
        keyBindings.put(KeyEvent.VK_LEFT,Action.CAMERA_MOVE_LEFT);
        keyBindings.put(KeyEvent.VK_RIGHT,Action.CAMERA_MOVE_RIGHT);
        keyBindings.put(KeyEvent.VK_ESCAPE, Action.EXIT);
        keyBindings.put(KeyEvent.VK_W,Action.MOVE_UP);
        keyBindings.put(KeyEvent.VK_S,Action.MOVE_DOWN);
        keyBindings.put(KeyEvent.VK_D,Action.MOVE_RIGHT);
        keyBindings.put(KeyEvent.VK_A,Action.MOVE_LEFT);
        keyBindings.put(KeyEvent.VK_ADD,Action.INCREASE_ZOOM);
        keyBindings.put(KeyEvent.VK_SUBTRACT,Action.DECREASE_ZOOM);
        keyBindings.put(KeyEvent.VK_ENTER,Action.CAMERA_FOLLOW);
        GameConfig gameConfig = new GameConfig(keyBindings);
        try{
            gameConfig.saveConfig(new File(System.getProperty("user.dir") + "/Config.cfg"));
        }catch (IOException e){
            System.err.print(e.getMessage());
        }

        try{
            loadConfig(new File(System.getProperty("user.dir") + "/Config.cfg"),player,mainWindow.getConfig());
        } catch (IOException e){
            System.err.println(e.getMessage());
        }

        mainLoop.run();
    }

    public static void loadConfig(File configFile, Actor a, GameWindowConfig c) throws IOException {
        GameConfig config = GameConfig.loadConfig(configFile);
        a.setKeyBindings(config.getKeyBindings());
        c.setKeyBindings(config.getKeyBindings());
    }
}
