package com.example;

import java.awt.Color;
import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * A set of function to process the image. Assumes camera is 10 inch from the surface with a 55mm lense.
 */
public class BananaImage {
    public BananaImage(BufferedImage image) {

    }

    /**
     * Determines if a given photo is a banana.
     * @param image The buffered image.
     * @param imageName The name of the image.
     * @return True if it is a banana, false if not.
     * @author Malahci
     */
    public static boolean isBanana(BufferedImage image, String imageName) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        boolean isBanana = false;
        int firstX = -1;
        int lastX = -1;
        for (int x = 0; x < imageWidth; x++) {
            for (int y = 0; y < imageHeight; y++) {
                Color color = new Color(image.getRGB(x, y));
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                float[] hsb = new float[3];
                Color.RGBtoHSB(r, g, b, hsb);

                float hue = hsb[0];
                float saturation = hsb[1];
                float brightness = hsb[2];
                if (hue > 0.1f && hue < 0.20f && saturation > 0.5f
                        && brightness > 0.5f) {
                    if (firstX == -1) {
                        firstX = x;
                    }
                    lastX = x;
                    image.setRGB(x, y, Color.MAGENTA.getRGB());
                    isBanana = true;
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        File outputFile = new File("demo\\src\\main\\java\\com\\example\\bananaImagesProcessed\\" + imageName);
        try {
            if (!ImageIO.write(image, "jpg", outputFile)) {
                System.out.println("did not work");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = lastX - firstX;
        System.out.println(firstX + ", " + lastX + ", " + length);
        return isBanana;
    }

    /**
     * Gets the user input on the photo to select.
     * @param scanner A scanner .
     * @return The name of the picture chosen.
     * @author Malahci.
     */
    public static String choosePicture(Scanner scanner) {
        String[] files;
        System.out.println("Select which image to use: ");
        File bananaDirectory = new File("demo\\src\\main\\java\\com\\example\\bananaImages");
        if (bananaDirectory.isDirectory()) {
            files = bananaDirectory.list();
        } else {
            System.err.println("Something went wrong with with the path name");
            return null;
        }
        for (int index = 0; index < files.length; index++) {
            System.out.println(files[index] + ",");
        }
        String imageName = scanner.nextLine();
        for (int index = 0; index < files.length; index++) {
            if (files[index].equals(imageName)) {
                return imageName;
            }
        }
        System.out.println("Please give a valid input");
        return null;
    }
}
