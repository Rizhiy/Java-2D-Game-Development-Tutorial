package com.rizhiy.tutorials.GameDevelopment.base;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by rizhiy on 23/04/16.
 */
public class ImageLoader {

    public static BufferedImage loadImageFrom(Class<?> classfile, String path) {
        URL url = classfile.getResource(path);
        BufferedImage img = null;

        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
