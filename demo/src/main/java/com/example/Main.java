package com.example;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Authors: Malachi, Carter
 * Date: 11/8/2025
 * Purpose: Takes a picture and detrrmines if its a banana. If it is, it finds
 * and prints out attributes of the banana.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        String imageName = null;
        Scanner scanner = new Scanner(System.in);
        while (imageName == null) {
            imageName = BananaImage.choosePicture(scanner);
        }
        BufferedImage image = ImageIO.read(new File("demo\\src\\main\\java\\com\\example\\bananaImages\\" + imageName));
        scanner.close();
        int bananaPixels = BananaImage.bananaDimensions(image, imageName);
        Banana b = new Banana(bananaPixels);
        System.out.println(bananaPixels);
        b.printBananaAttributes();

    }
}
