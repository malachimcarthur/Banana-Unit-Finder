package com.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * A set of function to process the image. Assumes camera is 10 inch from the
 * surface with a 55mm lense.
 */
public class BananaImage {

    public static int bananaDimensions(BufferedImage oldImage, String imageName) {
        System.out.println(oldImage.getWidth() + ", " + oldImage.getHeight());
        int imageHeight = 3024;
        int imageWidth = 4032;
        BufferedImage image;
        boolean flip = false;
        if (oldImage.getWidth() < oldImage.getHeight()) {
            flip = true;
        }
        image = StandardizedImage(oldImage, imageHeight, imageWidth, flip);

    public BananaImage(BufferedImage image) {

    }

    /**
     * Determines if a given photo is a banana.
     * 
     * @param image     the buffered image.
     * @param imageName the name of the image.
     * @return true if it is a banana, false if not.
     * @author Malahci
     */
    public static boolean isBanana(BufferedImage image, String imageName) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        boolean isBanana = false;
        int firstX = -1;
        int lastX = -1;
        int longestX = -1;
        int firstY = -1;
        int lastY = -1;
        int longestY = -1;
        for (int y = 0; y < imageHeight; y++) {
            firstX = -1;
            for (int x = 0; x < imageWidth; x++) {
                float[] hsb = getHsb(image, x, y);
                float hue = hsb[0];
                float saturation = hsb[1];
                float brightness = hsb[2];
                if (hue > BananaConstants.HUE_LOWER_BOUND && hue < BananaConstants.HUE_UPPER_BOUND
                        && saturation > BananaConstants.SATURATION
                        && brightness > BananaConstants.BRIGHTNESS) {

                    if (firstX == -1) {
                        firstX = x;
                    }
                    lastX = x;
                    int length = lastX - firstX;
                    if (length > longestX) {
                        longestX = length;
                    }

                    image.setRGB(x, y, Color.MAGENTA.getRGB());
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

        int realLength = Math.max(longestX, longestY);

        return realLength;
    }

    private static float[] getHsb(BufferedImage image, int x, int y) {
        Color color = new Color(image.getRGB(x, y));
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        float[] hsb = new float[3];
        Color.RGBtoHSB(r, g, b, hsb);
        return hsb;
    }

    /**
     * Gets the user input on the photo to select.
     * 
     * @param scanner a scanner
     * @return the nameof the picture chosen.
     * @author Malahci
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

    private static BufferedImage StandardizedImage(BufferedImage image, int newHeight, int newWidth, boolean flip) {
        if (flip) {
            // Flip the image horizontally
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image = op.filter(image, null);
        }
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2d = newImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // For
                                                                                                             // smoother
                                                                                                             // scaling
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return newImage;
    }
}
