package com.rizhiy.tutorials.GameDevelopment.base;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by rizhiy on 27/04/16.
 */
public abstract class IDRenderLoop extends BasicLoop {

    private int width;
    private int height;

    private Rectangle drawingArea;

    protected Graphics2D graphics2D;
    private BufferedImage background;


    public IDRenderLoop(int width, int height, int refreshRate) {
        super(refreshRate);
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setFocusable(false);
        requestFocus();

        init();
    }

    @Override
    public void work(double deltaTime){
        render();
    }

    @Override
    public void init() {
        background = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) background.getGraphics();
        drawingArea = new Rectangle(width,height);
    }


    public void render() {
        graphics2D.clearRect(0, 0, width, height);
    }

    public void clear() {
        Graphics g2 = getGraphics();
        if (background != null) {
            g2.drawImage(background, 0, 0, null);
        }
    }

    public boolean isInside(Point point){
        return drawingArea.contains(point);
    }
}
