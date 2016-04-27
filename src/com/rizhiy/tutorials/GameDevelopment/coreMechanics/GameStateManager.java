package com.rizhiy.tutorials.GameDevelopment.coreMechanics;

import java.awt.*;
import java.util.Stack;

/**
 * Created by rizhiy on 23/04/16.
 */
public class GameStateManager {

    public static Stack<GameState> states;

    public GameStateManager(){
        states = new Stack<>();
        states.push(new GameState());
    }

    public static void tick(double deltaTime){
        states.peek().tick(deltaTime);
    }

    public static void render(Graphics2D g){
        states.peek().render(g);
    }

    public static void init() {
        states.peek().init();
    }
}
